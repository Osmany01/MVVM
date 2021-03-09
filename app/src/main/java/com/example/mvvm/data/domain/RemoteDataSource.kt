package com.example.mvvm.data.domain

interface RemoteDataSource {

    suspend fun getMovies(page: Int): List<MovieModel>
}