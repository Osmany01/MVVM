package com.example.mvvm.data.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout

class MoviesRepository(private val localDataSource: LocalDataSource,
                       private val remoteDataSource: RemoteDataSource) {

    fun getMovies(): Flow<List<MovieModel>> = localDataSource.getMovies()

    suspend fun checkRequiredNewPage(lasVisible: Int) {

        val size = localDataSource.size()
        if (lasVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = withTimeout(5_000) {
                remoteDataSource.getMovies(page)
            }
            localDataSource.saveMovies(newMovies)
        }

    }

    companion object {

        private const val PAGE_SIZE = 20
        private const val PAGE_THRESHOLD = 10
    }
}