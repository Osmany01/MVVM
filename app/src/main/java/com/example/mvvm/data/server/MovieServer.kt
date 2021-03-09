package com.example.mvvm.data.server

import com.google.gson.annotations.SerializedName

data class MovieServer(
    val id: Int,
    @SerializedName("poster_path")
    val imageUrl: String,
    val title: String,
    var isFavorite: Boolean = false
)