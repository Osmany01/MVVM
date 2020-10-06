package com.example.mvvm.ui.popularmovies.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.data.api.IMAGE_BASE_URL
import com.example.mvvm.data.model.MovieModel
import com.example.mvvm.ui.moviedetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class PopularMoviePagedAdapter(private val context: Context, private val listener: OnFavoriteClickListener) :
    PagedListAdapter<MovieModel, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MovieViewHolder).bind(getItem(position), context, listener)
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieModel>() {

        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {

            return oldItem == newItem
        }
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieModel?, context: Context, listener: OnFavoriteClickListener) {

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
            itemView.iv_fav.setOnClickListener {
                listener.onFavoriteClick(movie)
                if (movie != null) {
                    movie.isFavorite = !movie.isFavorite
                    (it as ImageView).setImageResource(if (movie.isFavorite) R.drawable.ic_star_selected else R.drawable.ic_star)
                }
            }
        }
    }
}