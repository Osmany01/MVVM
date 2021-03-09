package com.example.mvvm.data.server

import com.example.mvvm.data.domain.MovieModel
import com.example.mvvm.data.domain.RemoteDataSource
import com.example.mvvm.data.toDomainMovie

class TheMovieDBDataSource(private val apiKey: String) : RemoteDataSource {

    override suspend fun getMovies(page: Int): List<MovieModel> =
        TheMovieDb.service
            .listPopularMoviesAsync(apiKey, page)
            .results
            .map {
                it.toDomainMovie()
            }

}