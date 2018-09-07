package com.asanarebel.githubapp.feature.repo.model

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class RepoOwner @ParcelConstructor constructor(val id: Long,
                                                    val login: String,
                                                    @SerializedName("avatar_url") val avatarUrl: String)