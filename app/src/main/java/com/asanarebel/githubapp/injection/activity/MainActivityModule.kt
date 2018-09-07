package com.asanarebel.githubapp.injection.activity

import androidx.appcompat.app.AppCompatActivity
import com.asanarebel.githubapp.feature.MainActivity
import dagger.Binds
import dagger.Module

/**
 * Sets up injection for MainActivity and the Android framework components under it (e.g. Fragment, ViewModel).
 */
@Module(
    includes = [
        ActivityModule::class,
        ViewModelModule::class,
        //features modules
        SearchRepoModule::class,
        RepoDetailModule::class
    ]
)
abstract class MainActivityModule {
    @Binds
    @PerActivity
    abstract fun bindAppCompatActivity(activity: MainActivity): AppCompatActivity
}