package com.asanarebel.githubapp.injection.activity

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier
import javax.inject.Scope

@Module
abstract class ActivityModule {
    @Binds
    @PerActivity
    abstract fun activity(activity: AppCompatActivity): Activity

    @Binds
    @PerActivity
    @ActivityContext
    abstract fun activityContext(activity: Activity): Context
}

@Scope
annotation class PerActivity

@Qualifier
annotation class ActivityContext