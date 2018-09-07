package com.asanarebel.githubapp.feature.repo.search.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.asanarebel.githubapp.feature.repo.model.Repo
import com.asanarebel.githubapp.network.service.GithubApiService
import io.reactivex.disposables.CompositeDisposable

class SearchDataSourceFactory(
        private val searchQuery: String,
        private val githubApi: GithubApiService,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Repo>() {

    val source = MutableLiveData<SearchRepoDataSource>()

    override fun create(): DataSource<Int, Repo> {
        val source = SearchRepoDataSource(searchQuery, githubApi, compositeDisposable)
        this.source.postValue(source)
        return source
    }
}