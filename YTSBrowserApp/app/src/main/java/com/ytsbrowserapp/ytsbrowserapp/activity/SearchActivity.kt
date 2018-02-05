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
    var call: Call<MoviesResponse> = ServiceFactory().movieListService().getDefaultMovieList(queryTerm = "", limit = 50)

    private enum class QueryStatus(val status: String) {
        RUNNING("running"), RESPONSE_OK("responseOk"), RESPONSE_EMPTY("responseEmpty"), FAILURE("failure"), NO_QUERY("noQuery")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        setupMovieRecyclerView()

        setupSearchView()
    }

    private fun setupSearchView() {
        search_view.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                setupViewByQueryStatus(QueryStatus.RUNNING)
                if (newText.isNotEmpty()) {
                    call = ServiceFactory().movieListService().getDefaultMovieList(queryTerm = newText, limit = 50)
                    call.enqueue(object : Callback<MoviesResponse> {
                        override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                            if (response!!.body()!!.data.movieCount > 0) {
                                movies = response.body()!!.data.movies
                                setupViewByQueryStatus(QueryStatus.RESPONSE_OK)
                            } else {
                                movies = emptyList()
                                setupViewByQueryStatus(QueryStatus.RESPONSE_EMPTY)
                            }
                        }

                        override fun onFailure(call: Call<MoviesResponse>, t: Throwable?) {
                            if (!call.isCanceled) {
                                setupViewByQueryStatus(QueryStatus.FAILURE)
                            }
                        }
                    })
                } else {
                    setupViewByQueryStatus(QueryStatus.NO_QUERY)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean = false

        })
    }

    private fun setupViewByQueryStatus(queryStatus: QueryStatus) {
        when (queryStatus) {
            QueryStatus.RUNNING -> {
                call.cancel()

                recycler_view_movies.visibility = View.GONE
                relative_layout_query_message.visibility = View.VISIBLE
                text_view_query_message.visibility = View.GONE
                progress_bar_searching.visibility = View.VISIBLE
            }
            QueryStatus.NO_QUERY -> {
                movies = emptyList()
                recycler_view_movies.adapter = MoviesListAdapter(movies, this@SearchActivity)

                recycler_view_movies.visibility = View.GONE
                relative_layout_query_message.visibility = View.VISIBLE
                text_view_query_message.visibility = View.VISIBLE
                text_view_query_message.text = resources.getString(R.string.search_activity_type_to_search)
                progress_bar_searching.visibility = View.GONE
            }
            QueryStatus.RESPONSE_OK -> {
                recycler_view_movies.adapter = MoviesListAdapter(movies, this@SearchActivity)

                recycler_view_movies.visibility = View.VISIBLE
                relative_layout_query_message.visibility = View.GONE
            }
            QueryStatus.RESPONSE_EMPTY -> {
                recycler_view_movies.adapter = MoviesListAdapter(movies, this@SearchActivity)

                recycler_view_movies.visibility = View.GONE
                relative_layout_query_message.visibility = View.VISIBLE
                text_view_query_message.visibility = View.VISIBLE
                text_view_query_message.text = resources.getString(R.string.search_activity_no_results)
                progress_bar_searching.visibility = View.GONE
            }
            QueryStatus.FAILURE -> {
                recycler_view_movies.visibility = View.GONE
                relative_layout_query_message.visibility = View.VISIBLE
                text_view_query_message.visibility = View.VISIBLE
                text_view_query_message.text = resources.getString(R.string.search_activity_failure)
                progress_bar_searching.visibility = View.GONE
            }
        }
    }

    private fun setupMovieRecyclerView() {
        val adapter = MoviesListAdapter(movies, this)
        recycler_view_movies.layoutManager = GridLayoutManager(this@SearchActivity, adapter.getNumberOfCovers())
        recycler_view_movies.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
