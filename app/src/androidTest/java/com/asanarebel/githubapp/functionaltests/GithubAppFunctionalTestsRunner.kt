package com.asanarebel.githubapp.functionaltests

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.asanarebel.githubapp.GithubApplication

class GithubAppFunctionalTestsRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader,
                                className: String,
                                context: Context): Application {
        return Instrumentation.newApplication(GithubApplication::class.java, context)
    }
}