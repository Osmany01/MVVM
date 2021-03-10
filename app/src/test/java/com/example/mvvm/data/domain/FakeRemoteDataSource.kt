package com.example.mvvm.data.domain

import kotlinx.coroutines.delay

class FakeRemoteDataSource(
    private val movies: List<MovieModel> = emptyList(),
    private val delay: Long = 0
): RemoteDataSource {

    override suspend fun getMovies(page: Int): List<MovieModel> {
        delay(delay)
        return movies
    }
}