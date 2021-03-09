/*
package com.example.mvvm.ui.moviedetails

import androidx.lifecycle.LiveData

import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiQueries: MoviesApiQueries) {

    lateinit var movieDetailsNetwork: MovieDetailsNetwork

    fun synchronizeMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int): LiveData<MovieDetailsModel> {

        movieDetailsNetwork = MovieDetailsNetwork(apiQueries, compositeDisposable)
        movieDetailsNetwork.synchronizeMovieDetails(movieId)

        return movieDetailsNetwork.movieDetailsResponse
    }

    fun getNetworkState(): LiveData<NetworkState> {

        return movieDetailsNetwork.networkState
    }
}*/
