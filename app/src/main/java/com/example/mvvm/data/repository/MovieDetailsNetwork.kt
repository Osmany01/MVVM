package com.example.mvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.data.api.MoviesApiQueries
import com.example.mvvm.data.model.MovieDetailsModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNetwork(
    private val apiQueries: MoviesApiQueries,
    private val compositeDisposable: CompositeDisposable
) {

    private val TAG = "movieDetailsNetwork"
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _movieDetailsResponse = MutableLiveData<MovieDetailsModel>()
    val movieDetailsResponse: LiveData<MovieDetailsModel>
        get() = _movieDetailsResponse

    fun synchronizeMovieDetails(movieId: Int) {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiQueries.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ movieDetailsModel ->
                        _movieDetailsResponse.postValue(movieDetailsModel)
                        _networkState.postValue(NetworkState.LOADED)
                    },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.d(TAG, it.message.toString())
                        }
                    )
            )

        } catch (e: Exception) {

            _networkState.postValue(NetworkState.ERROR)
            Log.d(TAG, e.message.toString())
        }
    }
}