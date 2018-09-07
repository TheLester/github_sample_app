package com.asanarebel.githubapp.extension

import android.view.View
import androidx.databinding.BindingAdapter

/**
* Sets existence (VISIBLE/GONE) with a boolean.
*/
@BindingAdapter("exists")
fun View.setExists(exists: Boolean) {
    visibility = if (exists) View.VISIBLE else View.GONE
}
