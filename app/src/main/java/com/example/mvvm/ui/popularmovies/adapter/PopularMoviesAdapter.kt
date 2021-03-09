package com.example.mvvm.ui.popularmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.data.domain.MovieModel
import com.example.mvvm.databinding.ItemMovieBinding
import com.example.mvvm.ui.utils.collectFlow
import com.example.mvvm.ui.utils.onClickEvents
import com.example.mvvm.ui.utils.toast
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PopularMoviesAdapter(private val scope: CoroutineScope) :
    ListAdapter<MovieModel, PopularMoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)  = with(holder) {

        val item = getItem(position)
        bind(item)

        scope.collectFlow(itemView.onClickEvents) {
            itemView.context.toast(item.title)
        }
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

        private val binding = ItemMovieBinding.bind(view)

        fun bind(movie: MovieModel) = with(binding){

            itemView.movie_title.text = movie.title

            val movieUrl: String = IMAGE_BASE_URL.plus(movie?.imageUrl)
            Glide.with(ivMainMovieImage.context)
                .load(movieUrl)
                .into(ivMainMovieImage)


           /* ivFav.setImageResource(if (movie.isFavorite) R.drawable.ic_star_selected else R.drawable.ic_star)

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
            }*/
        }
    }

    companion object {

        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185/"
    }
}