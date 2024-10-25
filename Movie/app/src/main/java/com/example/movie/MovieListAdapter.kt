package com.example.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.databinding.MovieCardBinding

class MovieCardViewHolder(view: View) : RecyclerView.ViewHolder(view)

class MovieListAdapter(private val context: Context) : RecyclerView.Adapter<MovieCardViewHolder>() {
    var movieList = listOf<MovieItem>()

    override fun getItemCount(): Int = movieList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieCardBinding.inflate(inflater, parent, false)

        return MovieCardViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        val itemBinding = MovieCardBinding.bind(holder.itemView)
        val resources = holder.itemView.resources
        val movieCard = movieList[position]

        itemBinding.movieTitle.text = movieCard.title
        itemBinding.movieRateValue.text = movieCard.rate.toString()
        itemBinding.movieDescription.text = movieCard.description
        Glide.with(holder.itemView.context)
            .load(movieCard.imageUrl)
            .into(itemBinding.movieImage)

        itemBinding.cardView.setOnClickListener {
            val intent = Intent(context, MovieActivity::class.java).apply {
                putExtra("MOVIE_TITLE", movieCard.title)
                putExtra("MOVIE_RATE", movieCard.rate)
                putExtra("MOVIE_DESCRIPTION", movieCard.description)
                putExtra("MOVIE_IMAGE_URL", movieCard.imageUrl)
            }
            context.startActivity(intent)
        }
    }
}