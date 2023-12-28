package com.example.movieskmm.domain.util

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val message: String? = null,
    val data: T? = null
)
