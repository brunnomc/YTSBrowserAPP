package com.ytsbrowserapp.ytsbrowserapp.model

import com.google.gson.annotations.SerializedName
import com.ytsbrowserapp.ytsbrowserapp.R

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
        @SerializedName("movies") val movies: List<MovieDTO> = emptyList()
)

class MovieData(
        @SerializedName("movie") val movie: Movie
)

class Movie(
        @SerializedName("id") var id: Long = 0,
        @SerializedName("title") var title: String = "",
        @SerializedName("title_long") var titleLong: String = "",
        @SerializedName("imdb_code") var imdbCode: String = "",
        @SerializedName("medium_cover_image") var mediumCoverImage: String = "",
        @SerializedName("large_cover_image") var largeCoverImage: String = "",
        @SerializedName("description_full") var descriptionFull: String = "",
        @SerializedName("genres") var genres: List<String> = emptyList(),
        @SerializedName("yt_trailer_code") var ytTrailerCode: String = "",
        @SerializedName("rating") var rating: Float = 0F,
        @SerializedName("runtime") var runtime: Int = 0,
        @SerializedName("language") var language: String = "",
        @SerializedName("cast") var cast: List<Cast> = emptyList(),
        @SerializedName("torrents") var torrents: List<Torrent> = emptyList()
)


class MovieDTO(
        @SerializedName("id") var id: Long = 0,
        @SerializedName("title_long") var titleLong: String = "",
        @SerializedName("medium_cover_image") var mediumCoverImage: String = ""
)


enum class MovieCategory(val labelId: Int, val genre: String = "all", val sortBy: String = "date_added", val quality: String = "all", val show: Boolean = true) {
    TOP_RATING(R.string.top_rating, "all", "rating"),
    LATEST(R.string.latest, "all", "date_added"),
    NEWEST(R.string.newest, "all", "year"),
    ACTION(R.string.action, "action", "date_added"),
    ADVENTURE(R.string.adventure, "adventure", "date_added"),
    ANIMATION(R.string.animation, "animation", "date_added"),
    COMEDY(R.string.comedy, "comedy", "date_added"),
    FAMILY(R.string.family, "family", "date_added"),
    CRIME(R.string.crime, "crime", "date_added"),
    THREE_DIMENSIONAL(R.string.three_dimensional, quality = "3D"),
    ROMANCE(R.string.sci_fi, "romance", "date_added"),
    THRILLER(R.string.thriller, "thriller", "date_added"),
    WAR(R.string.war, "war", "date_added"),
    WESTERN(R.string.western, "western", "date_added"),
    SCI_FI(R.string.sci_fi, "sci-fi", "date_added"),
    DOCUMENTARY(R.string.documentary, "documentary", "date_added"),
    DRAMA(R.string.drama, "drama", "date_added"),
    FANTASY(R.string.fantasy, "fantasy", "date_added"),
    BIOGRAPHY(R.string.biography, "biography", "date_added"),
    FILM_NOIR(R.string.film_noir, "film-noir", "date_added"),
    GAME_SHOW(R.string.game_show, "game-show", "date_added"),
    HiSTORY(R.string.history, "history", "date_added"),
    HORROR(R.string.horror, "horror", "date_added"),
    MUSIC(R.string.music, "music", "date_added", show = false),
    MUSICAL(R.string.musical, "musical", "date_added"),
    MYSTERY(R.string.mystery, "mystery", "date_added"),
    NEWS(R.string.news, "news", "date_added", show = false),
    REALITY_TV(R.string.reality_tv, "reality-tv", "date_added", show = false),
    SPORT(R.string.sport, "sport", "date_added"),
    TALK_SHOW(R.string.talk_show, "talk-show", "date_added", show = false)

}


