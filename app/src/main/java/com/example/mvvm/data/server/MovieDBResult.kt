package com.example.mvvm.data.server

import com.google.gson.annotations.SerializedName

data class MovieDBResult(
    val page: Int,
    val results: List<MovieServer>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)