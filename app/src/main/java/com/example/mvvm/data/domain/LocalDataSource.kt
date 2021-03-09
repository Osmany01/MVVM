package com.example.mvvm.data.domain

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun size(): Int
    suspend fun saveMovies(movies: List<MovieModel>)

    fun getMovies(): Flow<List<MovieModel>>
}