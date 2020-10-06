package com.example.mvvm.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.mvvm.data.api.FIRST_PAGE
import com.example.mvvm.data.api.MoviesApiQueries
import com.example.mvvm.data.model.MovieModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesNetwork(private val apiQueries: MoviesApiQueries, private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, MovieModel>() {

    private var page = FIRST_PAGE
    private val TAG = "moviesNetwork"

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>) {

        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiQueries.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .subscribe({ movieResponseModel ->

                    callback.onResult(movieResponseModel.movieList, null, page+1)
                    networkState.postValue(NetworkState.LOADED)
                },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.d(TAG, it.message.toString())
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiQueries.getPopularMovies(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({ movieResponseModel ->
                    if(movieResponseModel.totalPages >= params.key) {

                        callback.onResult(movieResponseModel.movieList, params.key+1)
                        networkState.postValue(NetworkState.LOADED)
                    }
                },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.d(TAG, it.message.toString())
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {}
}