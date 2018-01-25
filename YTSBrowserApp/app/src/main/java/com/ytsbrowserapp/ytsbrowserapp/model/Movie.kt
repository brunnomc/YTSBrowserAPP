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
        @SerializedName("movies") val movies: List<Movie> = emptyList()
)

class MovieData(
        @SerializedName("movie") val movie: Movie
)

class Movie(
        @SerializedName("id") var id: Long = 0,
        @SerializedName("title") var title: String = "",
        @SerializedName("title_long") var titleLong: String = "",
        @SerializedName("imdb_code") var imdbCode: String = "",
        @SerializedName("medium_cover_image") var mediumCoverImage : String = "",
        @SerializedName("large_cover_image") var largeCoverImage : String = "",
        @SerializedName("description_full") var descriptionFull : String = "",
        @SerializedName("genres") var genres : List<String> = emptyList(),
        @SerializedName("yt_trailer_code") var ytTrailerCode : String = "",
        @SerializedName("rating") var rating : Float = 0F,
        @SerializedName("runtime") var runtime : Int = 0,
        @SerializedName("language") var language : String = "",
        @SerializedName("cast") var cast : List<Cast> = emptyList(),
        @SerializedName("torrents") var torrents : List<Torrent> = emptyList()
)



