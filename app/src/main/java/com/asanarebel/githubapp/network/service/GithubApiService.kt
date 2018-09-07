package com.asanarebel.githubapp.network.service

import com.asanarebel.githubapp.feature.repo.model.ReposResponse
import com.asanarebel.githubapp.feature.repo.model.Watcher
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("/search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("page") page: Int): Single<ReposResponse>

    @GET("/repos/{owner}/{repo}/watchers")
    fun getWatchers(@Path("owner") owner: String,
                    @Path("repo") repo: String,
                    @Query("page") page: Int): Single<List<Watcher>>
}