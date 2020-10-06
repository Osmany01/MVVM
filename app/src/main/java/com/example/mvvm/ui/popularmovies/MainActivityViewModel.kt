package com.example.mvvm.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mvvm.data.model.MovieModel
import com.example.mvvm.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private val repositoryTopMovies: MoviesPagedRepository
    // private val repositorySearcher: SearchMoviePagedRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val currentQuery = MutableLiveData("")

    val moviePageList: LiveData<PagedList<MovieModel>> by lazy {

        repositoryTopMovies.synchronizedMovies(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {

        repositoryTopMovies.getNetworkState()
    }

  /*  val searchMoviePageList: LiveData<PagedList<ItemSearchMovieModel>> = currentQuery.switchMap {

        repositorySearcher.synchronizedSearchedMovie(it, compositeDisposable)
    }*/

    fun searchMovie(query: String) {

        currentQuery.postValue(query)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}