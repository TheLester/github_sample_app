package com.asanarebel.githubapp.feature.repo.search.data

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
        val status: Status,
        val throwable: Throwable? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(error: Throwable?) = NetworkState(Status.FAILED, error)
    }
}