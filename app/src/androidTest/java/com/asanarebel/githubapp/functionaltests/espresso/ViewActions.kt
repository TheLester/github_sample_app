package com.asanarebel.githubapp.functionaltests.espresso

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

class ViewActions private constructor() {

    companion object {

        fun noOp(): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "no-op"
                }

                override fun perform(uiController: UiController, view: View) {
                    // no-op
                }
            }
        }
    }
}