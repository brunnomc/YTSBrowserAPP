package com.ytsbrowserapp.ytsbrowserapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.ytsbrowserapp.ytsbrowserapp.R
import com.ytsbrowserapp.ytsbrowserapp.model.MovieResponse
import com.ytsbrowserapp.ytsbrowserapp.util.ServiceFactory
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.net.Uri
import com.ytsbrowserapp.ytsbrowserapp.model.Movie


class MovieActivity : AppCompatActivity() {
    var movieId: Long = 0L
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        if (intent != null) {
            movieId = intent.getLongExtra("movie_id", 0)
        }

        val call = ServiceFactory().movieListService().getMovieDetails(movieId)

        image_view_play_trailer.setOnClickListener({
            val url = "https://www.youtube.com/watch?v=" + movie.ytTrailerCode
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        })

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movie = response!!.body()!!.data.movie

                toolbar_layout.title = movie.titleLong
                text_view_synopsis.text = movie.descriptionFull
                text_view_genres.text = movie.genres.joinToString(" / ")
                text_view_rating.text = movie.rating.toString()
                text_view_language.text = movie.language
                text_view_runtime.text = String.format("%dhr %d min", (movie.runtime / 60), (movie.runtime % 60))
                text_view_quality.text = movie.torrents.joinToString (" / ") { it.quality }
                text_view_size.text = movie.torrents.joinToString (" / ") { it.size }

                Picasso.with(this@MovieActivity).load("https://img.youtube.com/vi/" + movie.ytTrailerCode + "/0.jpg").into(image_view_thumbnail)
                Picasso.with(this@MovieActivity).load(movie.largeCoverImage).into(image_view_cover)
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {}
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
