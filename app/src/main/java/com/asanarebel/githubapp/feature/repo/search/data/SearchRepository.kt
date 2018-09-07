package com.asanarebel.githubapp.feature.repo.search.data

import com.asanarebel.githubapp.feature.base.Listing
import com.asanarebel.githubapp.feature.repo.model.Repo

interface SearchRepository {

    fun searchRepositories(query: String, pageSize: Int): Listing<Repo>
}