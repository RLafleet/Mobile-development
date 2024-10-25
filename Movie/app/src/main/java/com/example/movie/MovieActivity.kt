package com.example.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movie.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: "Название фильма"
        val movieRate = intent.getDoubleExtra("MOVIE_RATE", 0.0)
        val movieDescription = intent.getStringExtra("MOVIE_DESCRIPTION") ?: "Описание фильма"
        val movieImageUrl = intent.getStringExtra("MOVIE_IMAGE_URL") ?: ""

        binding.moviePageTitle.text = movieTitle
        binding.moviePageDescription.text = movieDescription
        Glide.with(this)
            .load(movieImageUrl)
            .into(binding.moviePageImage)

        binding.moviePageArrowBack.setOnClickListener {
            finish()
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}