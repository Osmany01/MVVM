package com.example.mvvm.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.mvvm.data.domain.MovieModel
import com.example.mvvm.data.domain.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repositoryTopMovies: MoviesRepository
) : ViewModel() {

    private val _progressBar = MutableStateFlow(true)
    val progressBar: StateFlow<Boolean>
        get() = _progressBar

    val movies: Flow<List<MovieModel>>
        get() = repositoryTopMovies.getMovies()

    init {
        viewModelScope.launch {
            notifyLastVisible(0)
        }
    }

    suspend fun notifyLastVisible(lastVisible: Int) {
        repositoryTopMovies.checkRequiredNewPage(lastVisible)
        _progressBar.value = false
    }

}