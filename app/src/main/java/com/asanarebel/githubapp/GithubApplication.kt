package com.asanarebel.githubapp

import com.asanarebel.githubapp.injection.application.DaggerApplicationComponent
import com.asanarebel.githubapp.network.interceptor.DynamicUrlInterceptor
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class GithubApplication : DaggerApplication() {

    @Inject
    lateinit var dynamicUrlInterceptor: DynamicUrlInterceptor

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().create(this)

}