package com.example.mvvm.data.domain

data class MovieModel(val id: Int,
                      val imageUrl: String,
                      val title: String,
                      var isFavorite: Boolean = false)