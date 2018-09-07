package com.asanarebel.githubapp.injection.application

import com.asanarebel.githubapp.GithubApplication
import com.asanarebel.githubapp.network.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Scope

/**
 * Injects instances that live for the entire application.
 */
@PerApplication
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<GithubApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GithubApplication>()
}

/**
 * Scope for the top-level Dagger component.
 */
@Scope
annotation class PerApplication