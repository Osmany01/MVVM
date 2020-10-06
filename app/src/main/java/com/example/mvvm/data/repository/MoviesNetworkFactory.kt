package com.example.mvvm.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mvvm.data.api.MoviesApiQueries
import com.example.mvvm.data.model.MovieModel
import io.reactivex.disposables.CompositeDisposable


class MoviesNetworkFactory(
    private val apiQueries: MoviesApiQueries,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, MovieModel>() {

    val moviesLiveNetwork = MutableLiveData<MoviesNetwork>()
    override fun create(): DataSource<Int, MovieModel> {

        val moviesNetwork = MoviesNetwork(apiQueries, compositeDisposable)

        moviesLiveNetwork.postValue(moviesNetwork)
        return moviesNetwork
    }
}