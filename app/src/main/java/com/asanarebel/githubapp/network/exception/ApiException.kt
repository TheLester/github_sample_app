package com.asanarebel.githubapp.network.exception

import androidx.annotation.Nullable
import com.asanarebel.githubapp.network.model.ApiError

class ApiException constructor(@get:Nullable val apiError: ApiError) : RuntimeException()
