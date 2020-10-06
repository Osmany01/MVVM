package com.example.mvvm.data.api

import com.example.mvvm.data.model.MovieDetailsModel
import com.example.mvvm.data.model.MovieResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiQueries {


    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MovieResponseModel>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<MovieDetailsModel>
}