package com.asanarebel.githubapp.feature.repo.model

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Repo @ParcelConstructor constructor(val id: Long,
                val owner: RepoOwner,
                val name: String,
                val description: String,
                @SerializedName("watchers_count") val watchersCount: Int,
                @SerializedName("forks_count") val forksCount: Int) {
    val avatarUrl: String
        get() = owner.avatarUrl
}