package com.asanarebel.githubapp.feature.repo.detail.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.asanarebel.githubapp.extension.applySchedulers
import com.asanarebel.githubapp.feature.repo.model.Watcher
import com.asanarebel.githubapp.feature.repo.search.data.NetworkState
import com.asanarebel.githubapp.network.service.GithubApiService
import io.reactivex.disposables.CompositeDisposable

class WatchersDataSource(
        private val owner: String,
        private val repo: String,
        private val githubService: GithubApiService,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Watcher>() {


    val network = MutableLiveData<NetworkState>()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Watcher>) {
        val currentPage = 1
        val nextPage = currentPage + 1

        compositeDisposable.add(githubService
                .getWatchers(owner, repo,/*initial page*/currentPage)
                .doOnSubscribe {
                    postState(NetworkState.LOADING)
                }
                .doOnError {
                    postState(NetworkState.error(it))
                }
                .subscribe({
                    postState(NetworkState.LOADED)
                    callback.onResult(it, null, nextPage)
                }, {
                    it.printStackTrace()
                })
        )


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Watcher>) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        compositeDisposable.add(githubService
                .getWatchers(owner, repo,/*initial page*/currentPage)
                .applySchedulers()
                .doOnError {
                    postState(NetworkState.error(it))
                }
                .subscribe({
                    callback.onResult(it, nextPage)
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Watcher>) {
        //ignored
    }

    private fun postState(state: NetworkState) {
        network.postValue(state)
    }

}