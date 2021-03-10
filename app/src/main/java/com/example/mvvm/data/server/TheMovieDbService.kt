package com.example.mvvm.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMoviesAsync(
        @Query("api_key") apiKey: String? = "6e63c2317fbe963d76c3bdc2b785f6d1",
        @Query("page") page: Int
    ): MovieDBResult
}