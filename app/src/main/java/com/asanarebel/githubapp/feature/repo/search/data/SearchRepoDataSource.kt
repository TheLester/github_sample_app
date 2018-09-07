package com.asanarebel.githubapp.feature.repo.search.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.asanarebel.githubapp.extension.applySchedulers
import com.asanarebel.githubapp.feature.repo.model.Repo
import com.asanarebel.githubapp.network.service.GithubApiService
import io.reactivex.disposables.CompositeDisposable

class SearchRepoDataSource(
        private val query: String,
        private val githubService: GithubApiService,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Repo>() {

    val network = MutableLiveData<NetworkState>()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
        val currentPage = 1
        val nextPage = currentPage + 1

        compositeDisposable.add(githubService
                .searchRepositories(query,/*initial page*/currentPage)
                .doOnSubscribe {
                    postState(NetworkState.LOADING)
                }
                .doOnError {
                    postState(NetworkState.error(it))
                }
                .subscribe({
                    postState(NetworkState.LOADED)
                    callback.onResult(it.items, null, nextPage)
                }, {
                    it.printStackTrace()
                    postState(NetworkState.error(it))
                })
        )


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        compositeDisposable.add(githubService
                .searchRepositories(query,/*initial page*/currentPage)
                .applySchedulers()
                .doOnError {
                    postState(NetworkState.error(it))
                }
                .subscribe({
                    callback.onResult(it.items, nextPage)
                }, {
                    it.printStackTrace()
                    postState(NetworkState.error(it))
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        //ignored
    }

    private fun postState(state: NetworkState) {
        network.postValue(state)
    }

}