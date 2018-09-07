package com.asanarebel.githubapp.feature.repo.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asanarebel.githubapp.R
import com.asanarebel.githubapp.databinding.FragmentRepoDetailBinding
import com.asanarebel.githubapp.extension.getViewModel
import com.asanarebel.githubapp.feature.base.BaseFragment
import com.asanarebel.githubapp.feature.repo.model.Repo
import com.asanarebel.githubapp.feature.repo.search.data.Status
import com.asanarebel.githubapp.network.exception.ApiException
import com.asanarebel.githubapp.network.exception.NoNetworkException
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import org.parceler.Parcels
import javax.inject.Inject

class RepoDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var repo: Repo
    private lateinit var viewModel: RepoDetailViewModel

    companion object {
        private const val ARG_REPO = "repo"

        fun newInstance(repo: Repo): RepoDetailFragment {
            val args = Bundle()
            args.putParcelable(ARG_REPO, Parcels.wrap(repo))
            val fragment = RepoDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        repo = Parcels.unwrap(arguments!!.getParcelable<Parcelable>(ARG_REPO))
        viewModel = getViewModel(activity, viewModelFactory)
        toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }
        val adapter = WatchersAdapter()

        recycler.adapter = adapter
        FragmentRepoDetailBinding.bind(view).also {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
            it.repo = repo
        }

        viewModel.fetchWatchers(repo)
        viewModel.items.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            if (it.status == Status.FAILED) {
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
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.compositeDisposable.clear()
    }
}