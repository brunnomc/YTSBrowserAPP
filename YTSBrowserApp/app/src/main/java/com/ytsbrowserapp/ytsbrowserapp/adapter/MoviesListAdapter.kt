package com.ytsbrowserapp.ytsbrowserapp.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.ytsbrowserapp.ytsbrowserapp.R
import com.ytsbrowserapp.ytsbrowserapp.model.Movie
import kotlinx.android.synthetic.main.list_item_movies.view.*

class MoviesListAdapter(private val movies : List<Movie>) : RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>(){
    var context : Context? = null

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder?, position: Int) {

        Picasso.with(context).load(movies[position].mediumCoverImage).into(holder!!.cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviesListViewHolder {
        context = parent!!.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movies, parent, false)
        return MoviesListViewHolder(view)
    }

    class MoviesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cover = itemView.image_view_cover!!
    }
}