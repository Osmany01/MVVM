package com.example.mvvm.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342"
const val API_KEY = "6e63c2317fbe963d76c3bdc2b785f6d1"
const val BASE_URL = "https://api.themoviedb.org/3/"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

// https://api.themoviedb.org/3/movie/popular?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&page=1
// https://api.themoviedb.org/3/movie/299534?api_key=6e63c2317fbe963d76c3bdc2b785f6d1
// https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg

object MovieQueriesClient {

    fun getClient() : MoviesApiQueries {

        val requestInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiQueries::class.java)
    }
}