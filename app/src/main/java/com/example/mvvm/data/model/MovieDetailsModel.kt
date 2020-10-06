package com.example.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(
    val overview: String,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String
)