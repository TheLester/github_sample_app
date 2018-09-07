package com.asanarebel.githubapp.injection.application

import android.app.Application
import android.content.Context
import com.asanarebel.githubapp.GithubApplication
import com.asanarebel.githubapp.injection.activity.MainActivityModule
import com.asanarebel.githubapp.injection.activity.PerActivity
import com.asanarebel.githubapp.feature.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Qualifier

/**
 * Sets up injection for the application and the Android framework components under it (e.g. Activity, Service, etc.).
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ApplicationModule {
    @Binds
    @PerApplication
    abstract fun bindApplication(application: GithubApplication): Application

    @Binds
    @PerApplication
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity
}

/**
 * Qualifies Context instances as the Application's Context.
 */
@Qualifier
annotation class ApplicationContext