package com.asanarebel.githubapp.network.rx

import android.content.Context
import androidx.annotation.NonNull
import com.asanarebel.githubapp.extension.isNetworkAvailable
import com.asanarebel.githubapp.network.exception.ApiException
import com.asanarebel.githubapp.network.exception.NoNetworkException
import com.asanarebel.githubapp.network.model.ApiError
import com.asanarebel.githubapp.network.model.parseApiError
import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import java.io.IOException
import java.lang.reflect.Type
import java.net.SocketTimeoutException

class RxCallAdapterWrapper<R> internal constructor(private val wrapped: CallAdapter<R, *>,
                                                   private val context: Context,
                                                   private val gson: Gson) : CallAdapter<R, Any> {

    override fun responseType(): Type {
        return wrapped.responseType()
    }

    override fun adapt(@NonNull call: Call<R>): Any {
        val adaptedByWrapped = wrapped.adapt(call)

        if (adaptedByWrapped !is Single<*>) {
            throw IllegalStateException("Unable to adapt " + adaptedByWrapped.javaClass.toString())
        }

        return adaptedByWrapped
                .doOnSubscribe { _ ->
                    if (!context.isNetworkAvailable()) {
                        throw NoNetworkException()
                    }
                }
                .onErrorResumeNext { throwable -> Single.error(processException(throwable)) }
    }

    private fun processException(throwable: Throwable): Throwable {
        throwable.printStackTrace()
        return when (throwable) {
            is HttpException -> ApiException(gson.parseApiError(throwable.response().errorBody()))
            is SocketTimeoutException -> {
                val apiError = ApiError(context.getString(com.asanarebel.githubapp.R.string.error_timeout))
                ApiException(apiError)
            }
            is IOException -> NoNetworkException()
            else -> throwable
        }
    }
}