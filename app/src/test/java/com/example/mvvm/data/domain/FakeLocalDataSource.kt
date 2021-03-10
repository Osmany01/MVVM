package com.example.mvvm.data.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLocalDataSource: LocalDataSource {

    private val movies = mutableListOf<MovieModel>()

    override suspend fun size(): Int = movies.size

    override suspend fun saveMovies(movies: List<MovieModel>) {
        this.movies += movies
    }

    override fun getMovies(): Flow<List<MovieModel>> = flowOf(movies)


}