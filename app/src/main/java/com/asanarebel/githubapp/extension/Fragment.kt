package com.asanarebel.githubapp.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * Slightly simpler method to retrieve a Fragment-scoped ViewModel from within a fragment.
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(
        activity: FragmentActivity? = null,
        factory: ViewModelProvider.Factory? = null
): T {
    val clazz = T::class.java
    if (activity != null) return ViewModelProviders.of(activity, factory).get(clazz)
    return ViewModelProviders.of(this, factory).get(clazz)
}

/**
 * Retrieve a ViewModel in a fragment and execute a block of code with it.
 */
inline fun <reified T : ViewModel> Fragment.withViewModel(
        activity: FragmentActivity? = null,
        factory: ViewModelProvider.Factory? = null,
        block: T.() -> Unit
): T {
    val viewModel = getViewModel<T>(activity, factory)
    viewModel.block()
    return viewModel
}