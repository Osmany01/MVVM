package com.example.mvvm.ui.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.data.api.IMAGE_BASE_URL
import com.example.mvvm.data.api.MovieQueriesClient
import com.example.mvvm.data.api.MoviesApiQueries
import com.example.mvvm.data.model.MovieDetailsModel
import com.example.mvvm.data.repository.NetworkState
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var repository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId: Int = intent.getIntExtra("id", 1)

        val apiQueries: MoviesApiQueries = MovieQueriesClient.getClient()
        repository = MovieDetailsRepository(apiQueries)

        viewModel = getViewModel(movieId)
        viewModel.movieDetailsModel.observe(this, Observer { movieDetailsModel ->
            updateUI(movieDetailsModel)
        })

        viewModel.networkState.observe(this, Observer { networkState ->
            progress_bar.visibility = if (networkState == NetworkState.LOADING) View.VISIBLE else View.GONE

            tv_error.visibility = if (networkState == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun updateUI(movieDetailsModel: MovieDetailsModel?) {
        tv_movie_title.text = movieDetailsModel?.title
        tv_movie_description.text = movieDetailsModel?.overview

        val movieImageUrl: String = IMAGE_BASE_URL.plus(movieDetailsModel?.posterPath)
        Glide.with(this).load(movieImageUrl).into(iv_movie_image)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModel(movieId: Int) : MovieDetailsViewModel {

        return ViewModelProvider(this, object : Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return MovieDetailsViewModel(repository, movieId) as T
            }

        }).get(MovieDetailsViewModel::class.java)
    }
}
