package com.asanarebel.githubapp.feature.repo.detail.data

import com.asanarebel.githubapp.feature.base.Listing
import com.asanarebel.githubapp.feature.repo.model.Watcher

interface WatchersRepository {
    fun getWatchers(owner: String, repoName: String): Listing<Watcher>
}