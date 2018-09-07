package com.asanarebel.githubapp.feature.repo.search

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.asanarebel.githubapp.feature.repo.search.data.PagedSearchRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: PagedSearchRepository)
    : ViewModel() {

    private val searchQuery = MutableLiveData<String>()
    private val repoResult = map(searchQuery) {
        repository.searchRepositories(it, PAGE_SIZE)
    }

    val items = switchMap(repoResult) { it.pagedList }!!
    val compositeDisposable = repository.compositeDisposable
    val networkState = switchMap(repoResult) { it.networkState }!!

    fun showSearchResults(searchQuery: String): Boolean {
        if (this.searchQuery.value == searchQuery) {
            return false
        }
        this.searchQuery.value = searchQuery
        return true
    }
}