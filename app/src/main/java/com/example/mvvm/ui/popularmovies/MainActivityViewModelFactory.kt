package com.example.mvvm.ui.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.domain.MoviesRepository

class MainActivityViewModelFactory(
    private val repositoryTopMovies: MoviesRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(
                repositoryTopMovies
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}