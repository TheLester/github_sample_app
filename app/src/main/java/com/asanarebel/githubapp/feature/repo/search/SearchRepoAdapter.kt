package com.asanarebel.githubapp.feature.repo.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.asanarebel.githubapp.R
import com.asanarebel.githubapp.databinding.ViewHolderRepoItemBinding
import com.asanarebel.githubapp.feature.base.adapter.DataBoundListAdapter
import com.asanarebel.githubapp.feature.repo.model.Repo

class SearchRepoAdapter(
        private val repoClickCallback: (repo: Repo) -> Unit
) : DataBoundListAdapter<Repo, ViewHolderRepoItemBinding>(
        diffCallback = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem

            }
        }
) {

    override fun createBinding(parent: ViewGroup): ViewHolderRepoItemBinding {
        val binding = DataBindingUtil.inflate<ViewHolderRepoItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.view_holder_repo_item,
                parent,
                false
        )
        binding.root.setOnClickListener {
            binding.repo?.let {
                repoClickCallback.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ViewHolderRepoItemBinding, item: Repo?) {
        binding.repo = item
    }

}