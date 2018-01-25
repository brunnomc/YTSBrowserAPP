package com.ytsbrowserapp.ytsbrowserapp.model

import com.google.gson.annotations.SerializedName

class Torrent (
        @SerializedName("url") var url: String = "",
        @SerializedName("hash") var hash: String = "",
        @SerializedName("quality") var quality: String = "",
        @SerializedName("seeds") var seeds: Int = 0,
        @SerializedName("peers") var peers: Int = 0,
        @SerializedName("size") var size: String = "",
        @SerializedName("size_bytes") var sizeBytes: String = "",
        @SerializedName("date_uploaded") var dateUploaded: String = "",
        @SerializedName("date_uploaded_unix") var dateUploadedUnix: String = "")