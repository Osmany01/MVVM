package com.example.mvvm.ui.popularmovies

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.db.RoomDataSource
import com.example.mvvm.data.domain.MoviesRepository
import com.example.mvvm.data.server.TheMovieDBDataSource
import com.example.mvvm.databinding.ActivityMainBinding
//import com.example.mvvm.ui.favorites.FavoritesMoviesActivity
import com.example.mvvm.ui.popularmovies.adapter.PopularMoviesAdapter
import com.example.mvvm.ui.utils.app
import com.example.mvvm.ui.utils.collectFlow
import com.example.mvvm.ui.utils.lastVisibleEvents
import com.example.mvvm.ui.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.ArrayList


@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel = getViewModel()
            val popularMoviesAdapter = PopularMoviesAdapter(lifecycleScope)

            lifecycleScope.collectFlow(viewModel.progressBar) { progressBarMovies.visible}
            lifecycleScope.collectFlow(viewModel.movies) {popularMoviesAdapter.submitList(it)}

            lifecycleScope.collectFlow(rvMovies.lastVisibleEvents) {
                viewModel.notifyLastVisible(it)
            }

            rvMovies.adapter = popularMoviesAdapter
        }

       /* floating_button.setOnClickListener {
            if (!favoriteList.isNullOrEmpty()) {

                val intent = Intent(this, FavoritesMoviesActivity::class.java)
                intent.putParcelableArrayListExtra("favorites", favoriteList as ArrayList<out Parcelable>)
                startActivity(intent)
            }
        }*/
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModel(): MainActivityViewModel {

        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return MainActivityViewModel(
                    MoviesRepository(
                        RoomDataSource(app.db),
                        TheMovieDBDataSource(getString(R.string.api_key))
                    )
                )as T
            }
        }).get(MainActivityViewModel::class.java)
    }
}
