package com.asanarebel.githubapp.feature.repo.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.asanarebel.githubapp.R
import com.asanarebel.githubapp.databinding.ViewHolderRepoWatcherBinding
import com.asanarebel.githubapp.feature.base.adapter.DataBoundListAdapter
import com.asanarebel.githubapp.feature.repo.model.Watcher

class WatchersAdapter : DataBoundListAdapter<Watcher, ViewHolderRepoWatcherBinding>(
        diffCallback = object : DiffUtil.ItemCallback<Watcher>() {
            override fun areItemsTheSame(oldItem: Watcher, newItem: Watcher): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Watcher, newItem: Watcher): Boolean {
                return oldItem == newItem

            }
        }
) {
    override fun createBinding(parent: ViewGroup): ViewHolderRepoWatcherBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_holder_repo_watcher,
                parent,
                false
        )
    }

    override fun bind(binding: ViewHolderRepoWatcherBinding, item: Watcher?) {
        binding.watcher = item
    }
}