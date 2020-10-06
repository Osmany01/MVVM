package com.example.mvvm.data.model


import com.google.gson.annotations.SerializedName

data class MovieResponseModel(
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)