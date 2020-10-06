package com.example.mvvm.ui.popularmovies

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.api.MovieQueriesClient
import com.example.mvvm.data.api.MoviesApiQueries
import com.example.mvvm.data.model.MovieModel
import com.example.mvvm.data.repository.NetworkState
import com.example.mvvm.ui.favorites.FavoritesMoviesActivity
import com.example.mvvm.ui.popularmovies.adapter.OnFavoriteClickListener
import com.example.mvvm.ui.popularmovies.adapter.PopularMoviePagedAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity(), OnFavoriteClickListener {

    private lateinit var viewModel: MainActivityViewModel

    lateinit var repositoryMovies: MoviesPagedRepository
    private var favoriteList  = mutableListOf<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiQueries: MoviesApiQueries = MovieQueriesClient.getClient()
        repositoryMovies = MoviesPagedRepository(apiQueries)

        viewModel = getViewModel()

        val moviePagerAdapter = PopularMoviePagedAdapter(this, this)

        initMoviesRecyclerView(moviePagerAdapter)
        viewModel.moviePageList.observe(this, Observer { pagedListMovieModel ->

            moviePagerAdapter.submitList(pagedListMovieModel)
        })

        viewModel.networkState.observe(this, Observer { networkState ->
            progress_bar_movies.visibility =
                if (networkState == NetworkState.LOADING) View.VISIBLE else View.GONE

            tv_error_movies.visibility =
                if (networkState == NetworkState.ERROR) View.VISIBLE else View.GONE
        })

        floating_button.setOnClickListener {
            if (!favoriteList.isNullOrEmpty()) {

                val intent = Intent(this, FavoritesMoviesActivity::class.java)
                intent.putParcelableArrayListExtra("favorites", favoriteList as ArrayList<out Parcelable>)
                startActivity(intent)
            }
        }
    }

    private fun initMoviesRecyclerView(adapter: PopularMoviePagedAdapter) {

        val gridLayoutManager = GridLayoutManager(this, 3)
        rv_movies.layoutManager = gridLayoutManager
        rv_movies.setHasFixedSize(true)
        rv_movies.adapter = adapter
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModel(): MainActivityViewModel {

        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return MainActivityViewModel(repositoryMovies) as T
            }
        }).get(MainActivityViewModel::class.java)
    }

    override fun onFavoriteClick(movie: MovieModel?) {

        if (movie != null){
            if (favoriteList.remove(movie))
            else favoriteList.add(movie)
        }

    }
}
