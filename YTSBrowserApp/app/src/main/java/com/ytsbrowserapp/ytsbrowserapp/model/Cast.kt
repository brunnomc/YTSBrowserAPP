package com.ytsbrowserapp.ytsbrowserapp.model

import com.google.gson.annotations.SerializedName

class Cast(
        @SerializedName("name") var name: String = "",
        @SerializedName("character_name") var characterName: String = "",
        @SerializedName("url_small_image") var urlSmallImage: String = "",
        @SerializedName("imdb_code") var imdbCode: String = "")
