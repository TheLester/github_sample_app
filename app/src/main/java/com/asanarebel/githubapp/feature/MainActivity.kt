package com.asanarebel.githubapp.feature

import android.os.Bundle
import com.asanarebel.githubapp.R
import com.asanarebel.githubapp.feature.repo.search.SearchFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            return
        }

        //todo: add navigation library
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, SearchFragment())
                .commit()
    }
}
