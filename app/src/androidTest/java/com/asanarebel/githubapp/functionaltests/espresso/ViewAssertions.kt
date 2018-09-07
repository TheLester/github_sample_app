package com.asanarebel.githubapp.functionaltests.espresso

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion

class ViewAssertions private constructor() {

    companion object {

        fun recyclerViewShouldHaveItemsCount(count: Int): ViewAssertion {
            return ViewAssertion {
                view, _ ->
                val recyclerView = view as RecyclerView
                val actualCount = recyclerView.adapter!!.itemCount

                if (actualCount != count) {
                    throw AssertionError("RecyclerView has $actualCount while expected $count")
                }
            }
        }
    }
}
