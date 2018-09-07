package com.asanarebel.githubapp.network.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import java.io.IOException


data class ApiError constructor(@SerializedName("message") val message: String)

fun Gson.parseApiError(responseBody: ResponseBody?): ApiError {
    return try {
        this.fromJson(responseBody?.string(), ApiError::class.java)
    } catch (e: IOException) {
        e.printStackTrace()
        ApiError("")
    }
}