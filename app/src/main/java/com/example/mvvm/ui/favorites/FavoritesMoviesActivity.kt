package com.example.mvvm.ui.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.model.MovieModel
import com.example.mvvm.ui.favorites.adapter.FavoritesMovieAdapter
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesMoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        val favoritesMovieAdapter = FavoritesMovieAdapter(this, intent.getParcelableArrayListExtra<MovieModel>("favorites") as MutableList<MovieModel>)
        initRecyclerView(favoritesMovieAdapter)
    }

    private fun initRecyclerView(adapter: FavoritesMovieAdapter) {


        rv_favorites.layoutManager = GridLayoutManager(this, 3)
        rv_favorites.setHasFixedSize(true)
        rv_favorites.adapter = adapter
    }
}
