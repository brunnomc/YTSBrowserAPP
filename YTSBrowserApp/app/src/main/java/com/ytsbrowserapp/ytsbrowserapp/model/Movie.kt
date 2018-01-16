package com.ytsbrowserapp.ytsbrowserapp.model

import com.google.gson.annotations.SerializedName

class MoviesResponse(
        @SerializedName("status") val status: String,
        @SerializedName("status_message") val statusMessage: String,
        @SerializedName("data") val data: MoviesData)

class MovieResponse(
        @SerializedName("status") val status: String,
        @SerializedName("status_message") val statusMessage: String,
        @SerializedName("data") var data: MovieData)

class MoviesData(
        @SerializedName("movie_count") val movieCount: Long,
        @SerializedName("limit") val limit: Int,
        @SerializedName("page_number") val pageNumber: Int,
        @SerializedName("movies") val movies: List<Movie>
)

class MovieData(
        @SerializedName("movie") val movie: Movie
)

class Movie(
        @SerializedName("id") var id: Long = 0,
        @SerializedName("title") var title: String = "",
        @SerializedName("imdb_code") var imdbCode: String = "",
        @SerializedName("medium_cover_image") var mediumCoverImage : String = ""
)