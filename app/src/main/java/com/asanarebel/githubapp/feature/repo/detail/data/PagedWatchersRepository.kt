package com.asanarebel.githubapp.feature.repo.detail.data

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.asanarebel.githubapp.feature.base.Constants
import com.asanarebel.githubapp.feature.base.Listing
import com.asanarebel.githubapp.feature.repo.model.Watcher
import com.asanarebel.githubapp.network.service.GithubApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PagedWatchersRepository @Inject constructor(private val apiService: GithubApiService) :
        WatchersRepository {

    val compositeDisposable = CompositeDisposable()

    override fun getWatchers(owner: String, repoName: String): Listing<Watcher> {
        val factory = WatchersDataSourceFactory(owner,repoName, apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE * 2)
                .setPageSize(Constants.PAGE_SIZE)
                .build()

        val livePagedList = LivePagedListBuilder(factory, config)
                .build()


        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(factory.source) { it.network }
        )
    }

}