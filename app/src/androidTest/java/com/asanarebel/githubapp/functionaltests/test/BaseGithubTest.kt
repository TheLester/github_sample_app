package com.asanarebel.githubapp.functionaltests.test

import androidx.test.rule.ActivityTestRule
import com.asanarebel.githubapp.feature.MainActivity
import com.asanarebel.githubapp.functionaltests.rule.MockWebServerRule
import com.asanarebel.githubapp.functionaltests.screen.BaseScreen
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain

open class BaseGithubTest {
    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    lateinit var baseScreen: BaseScreen

    @get:Rule
    var rules = RuleChain.emptyRuleChain()
            .around(MockWebServerRule(this))
            .around(activityTestRule)

    @Before
    fun beforeEachTest() {
        baseScreen = BaseScreen()
    }

}
