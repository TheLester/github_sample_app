package com.asanarebel.githubapp.feature.repo.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.asanarebel.githubapp.R
import com.asanarebel.githubapp.extension.closeKeyboard
import com.asanarebel.githubapp.extension.getViewModel
import com.asanarebel.githubapp.feature.base.BaseFragment
import com.asanarebel.githubapp.feature.repo.detail.RepoDetailFragment
import com.asanarebel.githubapp.feature.repo.model.Repo
import com.asanarebel.githubapp.feature.repo.search.data.Status
import com.asanarebel.githubapp.network.exception.ApiException
import com.asanarebel.githubapp.network.exception.NoNetworkException
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = getViewModel(activity, viewModelFactory)
        initAdapter()
        fragmentSearchInput.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchGithub(fragmentSearchInput.text.toString())
                activity?.closeKeyboard()
                true
            } else {
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.compositeDisposable.clear()
    }

    private fun initAdapter() {
        val adapter = SearchRepoAdapter {
            //todo: add navigation library
            fragmentManager!!.beginTransaction()
                    .replace(R.id.container, RepoDetailFragment.newInstance(it))
                    .addToBackStack(null)
                    .commit()
        }

        recycler.adapter = adapter
        viewModel.items.observe(this, Observer<PagedList<Repo>> {
            adapter.submitList(it)
            emptyReposText.visibility = if (it.size > 0) View.GONE else View.VISIBLE
        })

        viewModel.networkState.observe(this, Observer {
            when (it.status) {
                Status.RUNNING -> {
                    swipeRefresh.isEnabled = true
                    swipeRefresh.isRefreshing = true
                }
                Status.FAILED -> {
                    if (it.throwable != null) {
                        val message = if (it.throwable is NoNetworkException) {
                            context?.getString(R.string.error_network)
                        } else if (it.throwable is ApiException) {
                            it.throwable.apiError.message
                        } else {
                            it.throwable.message
                        }
                        Snackbar.make(root, message.toString(), Snackbar.LENGTH_LONG).show()
                    }
                    swipeRefresh.isEnabled = false
                    swipeRefresh.isRefreshing = false
                }
                Status.SUCCESS -> {
                    swipeRefresh.isEnabled = false
                    swipeRefresh.isRefreshing = false
                }
            }
        })
    }


    private fun searchGithub(searchQuery: String) {
        searchQuery.trim().let {
            if (it.isNotEmpty()) {
                if (viewModel.showSearchResults(it)) {
                    recycler.scrollToPosition(0)
                    (recycler.adapter as? SearchRepoAdapter)?.submitList(null)
                }
            }
        }
    }
}
