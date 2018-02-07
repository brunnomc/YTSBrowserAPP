package com.ytsbrowserapp.ytsbrowserapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ytsbrowserapp.ytsbrowserapp.R
import com.ytsbrowserapp.ytsbrowserapp.adapter.MoviesListAdapter
import com.ytsbrowserapp.ytsbrowserapp.model.MovieCategory
import com.ytsbrowserapp.ytsbrowserapp.model.MoviesResponse
import com.ytsbrowserapp.ytsbrowserapp.util.ServiceFactory
import kotlinx.android.synthetic.main.fragment_movies_list.view.*
import kotlinx.android.synthetic.main.list_item_movie_category.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_movies_list, container, false)

        setupRecyclerViews(view)

        return view
    }

    private fun setupRecyclerViews(view: View) {
        for (category in MovieCategory.values()) {
            if (category.show) {
                val call = ServiceFactory().movieListService().getDefaultMovieList(genre = category.genre, sortBy = category.sortBy, quality = category.quality)
                call.enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                        if (response!!.body()!!.data.movieCount > 0) {
                            val newView = activity.layoutInflater.inflate(R.layout.list_item_movie_category, null)
                            newView.text_view_label.text = context.getString(category.labelId)
                            val movies = response.body()!!.data.movies
                            newView.recycler_view_movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            newView.recycler_view_movies.adapter = MoviesListAdapter(movies, context)
                            view.linear_layout.addView(newView)
                        }
                    }

                    override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {}
                })
            }
        }
    }

}

