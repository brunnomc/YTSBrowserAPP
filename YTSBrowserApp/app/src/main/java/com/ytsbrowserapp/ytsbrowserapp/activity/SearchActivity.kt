package com.ytsbrowserapp.ytsbrowserapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.view.MenuItem
import android.view.View
import com.ytsbrowserapp.ytsbrowserapp.R
import com.ytsbrowserapp.ytsbrowserapp.adapter.MoviesListAdapter
import com.ytsbrowserapp.ytsbrowserapp.model.Movie
import com.ytsbrowserapp.ytsbrowserapp.model.MoviesResponse
import com.ytsbrowserapp.ytsbrowserapp.util.ServiceFactory
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    var movies: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        val adapter = MoviesListAdapter(movies, this)
        recycler_view_movies.layoutManager = GridLayoutManager(this@SearchActivity, adapter.getNumberOfCovers())
        recycler_view_movies.adapter = adapter

        search_view.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                if (newText.isNotEmpty()) {
                    val call = ServiceFactory().movieListService().getDefaultMovieList(queryTerm = newText, limit = 50)
                    call.enqueue(object : Callback<MoviesResponse> {
                        override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {

                            if (response!!.body()!!.data.movieCount > 0) {
                                recycler_view_movies.visibility = View.VISIBLE
                                relative_layout_query_message.visibility = View.GONE
                                movies = response.body()!!.data.movies
                                recycler_view_movies.adapter = MoviesListAdapter(movies, this@SearchActivity)
                            } else {
                                recycler_view_movies.visibility = View.GONE
                                relative_layout_query_message.visibility = View.VISIBLE
                                text_view_query_message.text = resources.getString(R.string.search_activity_no_results)
                                movies = emptyList()
                                recycler_view_movies.adapter = MoviesListAdapter(movies, this@SearchActivity)
                            }
                        }

                        override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {

                        }
                    })
                } else {
                    movies = emptyList()
                    recycler_view_movies.adapter = MoviesListAdapter(movies, this@SearchActivity)
                    recycler_view_movies.visibility = View.GONE
                    relative_layout_query_message.visibility = View.VISIBLE
                    text_view_query_message.text = resources.getString(R.string.search_activity_type_to_search)
                }


                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                //Task HERE
                return false
            }

        })

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
