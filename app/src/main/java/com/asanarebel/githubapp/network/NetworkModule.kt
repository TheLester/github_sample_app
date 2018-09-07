package com.asanarebel.githubapp.network

import android.content.Context
import com.asanarebel.githubapp.BuildConfig
import com.asanarebel.githubapp.feature.base.Constants.Companion.BASE_URL
import com.asanarebel.githubapp.injection.application.ApplicationContext
import com.asanarebel.githubapp.injection.application.PerApplication
import com.asanarebel.githubapp.network.interceptor.DynamicUrlInterceptor
import com.asanarebel.githubapp.network.rx.RxErrorHandlingCallAdapterFactory
import com.asanarebel.githubapp.network.service.GithubApiService
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {
    @Provides
    @PerApplication
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @PerApplication
    fun provideDynamicUrlInterceptor(): DynamicUrlInterceptor {
        return DynamicUrlInterceptor(BASE_URL)
    }

    @Provides
    @PerApplication
    fun provideGithubApiService(client: OkHttpClient, @ApplicationContext context: Context, gson: Gson): GithubApiService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(context, gson))
                .build()
                .create(GithubApiService::class.java)
    }

    @Provides
    @PerApplication
    fun provideOkHttp(dynamicUrlInterceptor: DynamicUrlInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor(dynamicUrlInterceptor)
        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(StethoInterceptor())
        }

        return client.build()
    }
}