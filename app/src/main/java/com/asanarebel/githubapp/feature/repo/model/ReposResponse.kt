package com.asanarebel.githubapp.feature.repo.model

import com.google.gson.annotations.SerializedName

data class ReposResponse(@SerializedName("total_count") val totalCount: Int,
                         @SerializedName("incomplete_results") val incompleteResults: Boolean,
                         val items: List<Repo>
)