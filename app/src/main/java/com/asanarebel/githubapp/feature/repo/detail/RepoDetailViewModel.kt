package com.asanarebel.githubapp.feature.repo.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.asanarebel.githubapp.feature.base.Listing
import com.asanarebel.githubapp.feature.repo.detail.data.PagedWatchersRepository
import com.asanarebel.githubapp.feature.repo.model.Repo
import com.asanarebel.githubapp.feature.repo.model.Watcher
import com.asanarebel.githubapp.feature.repo.search.data.Status
import javax.inject.Inject

class RepoDetailViewModel @Inject constructor(private val repository: PagedWatchersRepository)
    : ViewModel() {
    private var watchersResult = MutableLiveData<Listing<Watcher>>()

    val compositeDisposable = repository.compositeDisposable

    val items = Transformations.switchMap(watchersResult) { it.pagedList }!!
    val networkState = Transformations.switchMap(watchersResult) { it.networkState }!!
    val isInProgress = Transformations.switchMap(networkState) {
        MutableLiveData<Boolean>().also {
            it.value = networkState.value?.status == Status.RUNNING
        }
    }

    fun fetchWatchers(repo: Repo) {
        watchersResult.value = repository.getWatchers(
                repo.owner.login,
                repo.name)
    }
}
