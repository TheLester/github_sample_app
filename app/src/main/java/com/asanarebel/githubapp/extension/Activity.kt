package com.asanarebel.githubapp.extension

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.closeKeyboard() {
    val `in` = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    `in`.hideSoftInputFromWindow(this.currentFocus.windowToken, 0)
}