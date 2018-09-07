package com.asanarebel.githubapp.network.interceptor

import android.text.TextUtils
import okhttp3.Interceptor
import java.io.IOException
import java.net.URI

class DynamicUrlInterceptor(private var host: String?) : Interceptor {
    private var scheme: String? = null

    fun setSchemeAndHost(scheme: String, host: String) {
        this.scheme = scheme
        this.host = host
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        val host = this.host ?: return chain.proceed(request)

        val uri = URI.create(host)

        val urlBuilder = request.url().newBuilder()

        if (!TextUtils.isEmpty(scheme)) {
            urlBuilder.scheme(scheme!!)
        }

        if (uri.port != -1) {
            urlBuilder.port(uri.port)
        }

        val newUrl = urlBuilder
                .host(uri.host)
                .build()

        request = request.newBuilder()
                .url(newUrl)
                .build()
        return chain.proceed(request)
    }
}