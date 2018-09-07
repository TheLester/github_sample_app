package com.asanarebel.githubapp.feature.repo.model

import com.google.gson.annotations.SerializedName

data class Watcher(
        val id: Long,
        val login: String,
        @SerializedName("html_url") val url: String,
        @SerializedName("avatar_url") val avatarUrl: String
)