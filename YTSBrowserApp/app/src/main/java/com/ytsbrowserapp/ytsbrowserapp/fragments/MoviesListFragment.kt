package com.ytsbrowserapp.ytsbrowserapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.ytsbrowserapp.ytsbrowserapp.R
import com.ytsbrowserapp.ytsbrowserapp.adapter.MoviesListAdapter
import com.ytsbrowserapp.ytsbrowserapp.model.Movie
import com.ytsbrowserapp.ytsbrowserapp.model.MoviesResponse
import com.ytsbrowserapp.ytsbrowserapp.util.ServiceFactory
import kotlinx.android.synthetic.main.fragment_movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListFragment : Fragment() {

    var movies: List<Movie>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_movies_list, container, false)

        var call = ServiceFactory().movieListService().getDefaultMovieList(30, 0, "date_added", "all")

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                movies = response?.body()?.data?.movies

                recycler_view_movies1.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                recycler_view_movies1.adapter = MoviesListAdapter(movies!!)
            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {

            }
        })

        call = ServiceFactory().movieListService().getDefaultMovieList(30, 0, "date_added", "comedy")
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                val movies = response?.body()?.data?.movies

                recycler_view_movies2.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                recycler_view_movies2.adapter = MoviesListAdapter(movies!!)

            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {

            }
        })

        return view
    }

}

