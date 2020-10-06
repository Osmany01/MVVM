package com.example.mvvm.ui.popularmovies.adapter

import com.example.mvvm.data.model.MovieModel

interface OnFavoriteClickListener {

    fun onFavoriteClick(movie: MovieModel?)
}