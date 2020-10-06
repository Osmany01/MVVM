package com.example.mvvm.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mvvm.data.api.MoviesApiQueries
import com.example.mvvm.data.api.POST_PER_PAGE
import com.example.mvvm.data.model.MovieModel
import com.example.mvvm.data.repository.MoviesNetwork
import com.example.mvvm.data.repository.MoviesNetworkFactory
import com.example.mvvm.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MoviesPagedRepository(private val apiQueries: MoviesApiQueries) {

    lateinit var moviePageList: LiveData<PagedList<MovieModel>>
    lateinit var moviesNetworkFactory: MoviesNetworkFactory

    fun synchronizedMovies(compositeDisposable: CompositeDisposable): LiveData<PagedList<MovieModel>> {
        moviesNetworkFactory = MoviesNetworkFactory(apiQueries, compositeDisposable)

        val pageListListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(POST_PER_PAGE)
            .build()
        moviePageList = LivePagedListBuilder(moviesNetworkFactory, pageListListConfig).build()

        return moviePageList
    }


    fun getNetworkState(): LiveData<NetworkState> {

        return Transformations.switchMap(moviesNetworkFactory.moviesLiveNetwork, MoviesNetwork::networkState)
    }
}