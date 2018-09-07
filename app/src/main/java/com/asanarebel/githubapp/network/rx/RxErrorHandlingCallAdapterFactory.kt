package com.asanarebel.githubapp.network.rx

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory private constructor(private val context: Context,
                                                            private val gson: Gson) : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    override fun get(@NonNull returnType: Type,
                     @NonNull annotations: Array<Annotation>,
                     @NonNull retrofit: Retrofit): CallAdapter<*, *> {
        return RxCallAdapterWrapper(original.get(returnType, annotations, retrofit)!!, context, gson)
    }

    companion object {

        fun create(context: Context, gson: Gson): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory(context, gson)
        }
    }
}