package com.asanarebel.githubapp.feature.repo.detail.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.asanarebel.githubapp.feature.repo.model.Watcher
import com.asanarebel.githubapp.network.service.GithubApiService
import io.reactivex.disposables.CompositeDisposable

class WatchersDataSourceFactory(
        private val owner: String,
        private val repo: String,
        private val githubApi: GithubApiService,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Watcher>() {

    val source = MutableLiveData<WatchersDataSource>()

    override fun create(): DataSource<Int, Watcher> {
        val source = WatchersDataSource(owner,repo, githubApi, compositeDisposable)
        this.source.postValue(source)
        return source
    }
}