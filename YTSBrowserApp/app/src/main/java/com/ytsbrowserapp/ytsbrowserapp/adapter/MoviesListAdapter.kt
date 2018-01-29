package com.ytsbrowserapp.ytsbrowserapp.adapter


import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.ytsbrowserapp.ytsbrowserapp.R
import com.ytsbrowserapp.ytsbrowserapp.activity.MovieActivity
import com.ytsbrowserapp.ytsbrowserapp.model.Movie
import kotlinx.android.synthetic.main.list_item_movies.view.*

class MoviesListAdapter(val movies: List<Movie>, val context: Context) : RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {
    private val metrics = DisplayMetrics()
    private val windowManager = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
    private val coverMinimumWidth = 160
    private val coverSpaceBetween = 8

    init {
        windowManager.defaultDisplay.getMetrics(metrics)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder?, position: Int) {
        Picasso.with(context).load(movies[position].mediumCoverImage).into(holder!!.cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviesListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_movies, parent, false)
        return MoviesListViewHolder(view)
    }

    fun getNumberOfCovers(): Int{
        val numberOfCovers: Int = (metrics.widthPixels / (coverMinimumWidth * metrics.density)).toInt()
        return if (numberOfCovers < 3) 3 else numberOfCovers
    }

    fun getCoverWidth(): Int {
        val numberOfCovers = getNumberOfCovers()
        return ((metrics.widthPixels - (numberOfCovers + 1) * (coverSpaceBetween * metrics.density)) / numberOfCovers).toInt()
    }

    inner class MoviesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cover = itemView.image_view_thumbnail!!

        init {
            itemView.setOnClickListener({
                val intent = Intent(itemView.context, MovieActivity::class.java)
                intent.putExtra(MovieActivity.EXTRA_MOVIE_ID, movies[adapterPosition].id)
                context.startActivity(intent)
            })

            itemView.setOnLongClickListener({
                Toast.makeText(context, movies[adapterPosition].titleLong, Toast.LENGTH_LONG).show()
                return@setOnLongClickListener true
            })
            cover.layoutParams.width = getCoverWidth()
        }
    }
}