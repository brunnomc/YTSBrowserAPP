package com.ytsbrowserapp.ytsbrowserapp.service

import com.ytsbrowserapp.ytsbrowserapp.model.MovieResponse
import com.ytsbrowserapp.ytsbrowserapp.model.MoviesResponse
import com.ytsbrowserapp.ytsbrowserapp.util.Constants.Companion.MOVIE_DETAILS
import com.ytsbrowserapp.ytsbrowserapp.util.Constants.Companion.MOVIE_LIST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(MOVIE_LIST)
    fun getDefaultMovieList(
            @Query("limit") limit: Int = 20,
            @Query("page") page: Int = 1,
            @Query("sort_by") sortBy: String = "date_added",
            @Query("genre") genre: String = "all",
            @Query("query_term") queryTerm: String = ""): Call<MoviesResponse>

    @GET(MOVIE_DETAILS)
    fun getMovieDetails(
            @Query("movie_id") movieId: Long,
            @Query("with_images") withImages: Boolean = false,
            @Query("with_cast") withCast: Boolean = false): Call<MovieResponse>
}