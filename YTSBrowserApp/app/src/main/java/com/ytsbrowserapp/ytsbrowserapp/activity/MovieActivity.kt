package com.ytsbrowserapp.ytsbrowserapp.activity

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.ytsbrowserapp.ytsbrowserapp.R
import com.ytsbrowserapp.ytsbrowserapp.model.Movie
import com.ytsbrowserapp.ytsbrowserapp.model.MovieResponse
import com.ytsbrowserapp.ytsbrowserapp.util.Constants
import com.ytsbrowserapp.ytsbrowserapp.util.ServiceFactory
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieActivity : AppCompatActivity() {
    var movieId: Long = 0L
    lateinit var movie: Movie
    lateinit var dialog: AlertDialog

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        if (intent != null) {
            movieId = intent.getLongExtra(EXTRA_MOVIE_ID, 0)
        }
        setupApiCall()
        showProgress()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showProgress() {
        val builder = AlertDialog.Builder(this)
        dialog = builder.setView(layoutInflater.inflate(R.layout.progress_view, null)).create()
        dialog.setCancelable(false)
        dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.show()
    }

    private fun setupApiCall() {
        val call = ServiceFactory().movieListService().getMovieDetails(movieId)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movie = response!!.body()!!.data.movie
                setupViewComponentsOnResponse(movie)
            }
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {}
        })
    }

    private fun setupViewComponentsOnResponse(movie: Movie) {
        toolbar_layout.title = movie.titleLong
        text_view_synopsis.text = movie.descriptionFull
        text_view_genres.text = movie.genres.joinToString(" / ")
        text_view_rating.text = movie.rating.toString()
        text_view_language.text = movie.language
        text_view_runtime.text = String.format("%dhr %d min", (movie.runtime / 60), (movie.runtime % 60))
        text_view_quality.text = movie.torrents.joinToString(" / ") { it.quality }
        text_view_size.text = movie.torrents.joinToString(" / ") { it.size }

        Picasso.with(this@MovieActivity).load(Constants.YOUTUBE_THUMBNAIL_BASE_URL + movie.ytTrailerCode + Constants.YOUTUBE_THUMBNAIL_0_URL).into(image_view_thumbnail)
        Picasso.with(this@MovieActivity).load(movie.largeCoverImage).into(image_view_cover)

        dialog.dismiss()

        image_view_play_trailer.setOnClickListener({
            openUrl(Constants.YOUTUBE_VIDEO_URL + movie.ytTrailerCode)
        })
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
