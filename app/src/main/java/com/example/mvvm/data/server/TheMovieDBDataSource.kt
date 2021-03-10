package com.example.mvvm.data.server

import com.example.mvvm.data.domain.MovieModel
import com.example.mvvm.data.domain.RemoteDataSource
import com.example.mvvm.data.toDomainMovie

class TheMovieDBDataSource() : RemoteDataSource {

    override suspend fun getMovies(page: Int): List<MovieModel> =
        TheMovieDb.service
            .listPopularMoviesAsync(page = page)
            .results
            .map {
                it.toDomainMovie()
            }

}