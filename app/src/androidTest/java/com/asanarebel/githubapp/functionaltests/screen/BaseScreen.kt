package com.asanarebel.githubapp.functionaltests.screen

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.asanarebel.githubapp.R
import com.asanarebel.githubapp.functionaltests.espresso.ViewActions.Companion.noOp
import com.asanarebel.githubapp.functionaltests.espresso.ViewAssertions.Companion.recyclerViewShouldHaveItemsCount
import org.hamcrest.Matchers.allOf

class BaseScreen {


    fun shouldDisplayTitle(title: String): BaseScreen {
        onView(allOf(withText(title), withParent(withId(R.id.toolbar)))).check(matches(isDisplayed()))
        return this
    }

    fun searchWithQuery(query: String): BaseScreen {
        onView(withId(R.id.fragmentSearchInput))
                .perform(typeText(query), pressImeActionButton())
        return this
    }

    fun shouldDisplayRepoWithName(name: String): BaseScreen {
        onView(withId(R.id.recycler))
                .perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(
                        allOf(
                                withId(R.id.repo_item_name),
                                withText(name))),
                        noOp()))
        return this
    }

    fun shouldDisplayRepoWitForksCount(forks: String): BaseScreen {
        onView(withId(R.id.recycler))
                .perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(
                        allOf(
                                withId(R.id.repo_item_forks),
                                withText(forks))),
                        noOp()))
        return this
    }

    fun shouldDisplayRepoWithDescription(description: String): BaseScreen {
        onView(withId(R.id.recycler))
                .perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(
                        allOf(
                                withId(R.id.repo_item_name_description),
                                withText(description))),
                        noOp()))
        return this
    }

    fun shouldDisplayExpectedAmountOfRepos(reposCount: Int): BaseScreen {
        onView(withId(R.id.recycler)).check(recyclerViewShouldHaveItemsCount(reposCount))
        return this
    }

    fun shouldDisplayError(errorText: String): BaseScreen {
        onView(withText(errorText))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )))

        return this
    }

}