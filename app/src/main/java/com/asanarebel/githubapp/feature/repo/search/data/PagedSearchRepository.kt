package com.asanarebel.githubapp.feature.repo.search.data

import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.asanarebel.githubapp.feature.base.Listing
import com.asanarebel.githubapp.feature.repo.model.Repo
import com.asanarebel.githubapp.network.service.GithubApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PagedSearchRepository @Inject constructor(
        private val githubApi: GithubApiService
        ): SearchRepository {

    val compositeDisposable = CompositeDisposable()

    override fun searchRepositories(query: String, pageSize: Int): Listing<Repo> {

        val factory =  SearchDataSourceFactory(query,githubApi, compositeDisposable)

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(pageSize * 2)
                .setPageSize(pageSize)
                .build()

        val livePagedList = LivePagedListBuilder(factory, config)
                .build()


        return Listing(
                pagedList = livePagedList,
                networkState = switchMap(factory.source) { it.network }
        )
    }

}