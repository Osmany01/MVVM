package com.example.mvvm.ui.favorites.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.data.api.IMAGE_BASE_URL
import com.example.mvvm.data.model.MovieModel
import com.example.mvvm.ui.moviedetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoritesMovieAdapter(val context: Context, val favoriteList: MutableList<MovieModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {

        return favoriteList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavoriteMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as FavoriteMovieViewHolder).bind(favoriteList[position], context)
    }


    class FavoriteMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieModel?, context: Context) {

            itemView.movie_title.text = movie?.title

            val movieUrl: String = IMAGE_BASE_URL.plus(movie?.imageUrl)
            Glide.with(context)
                .load(movieUrl)
                .into(itemView.iv_main_movie_image)

            if (movie != null) {
                itemView.iv_fav.setImageResource(if (movie.isFavorite) R.drawable.ic_star_selected else R.drawable.ic_star)
            }
            itemView.iv_main_movie_image.setOnClickListener {

                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("id", movie?.id)
                context.startActivity(intent)
            }
        }
    }
}