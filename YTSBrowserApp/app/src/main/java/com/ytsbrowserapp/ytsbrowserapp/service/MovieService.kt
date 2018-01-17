package com.ytsbrowserapp.ytsbrowserapp.service

import com.ytsbrowserapp.ytsbrowserapp.model.MoviesResponse
import com.ytsbrowserapp.ytsbrowserapp.util.Constants.Companion.MOVIE_LIST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(MOVIE_LIST)
    fun getDefaultMovieList(
            @Query("limit") limit : Int,
            @Query("page") page : Int,
            @Query("sort_by") sortBy : String,
            @Query("genre") genre : String): Call<MoviesResponse>

}