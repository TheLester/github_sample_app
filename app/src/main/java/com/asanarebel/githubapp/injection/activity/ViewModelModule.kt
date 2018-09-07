package com.asanarebel.githubapp.injection.activity

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Provides a factory for creating and injecting ViewModels.
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelInjectionFactory(factory: ViewModelInjectionFactory): ViewModelProvider.Factory
}