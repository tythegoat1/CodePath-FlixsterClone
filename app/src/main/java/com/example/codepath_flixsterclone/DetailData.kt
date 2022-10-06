package com.example.codepath_flixsterclone


import com.google.gson.annotations.SerializedName

data class DetailData {
    @SerializedName("original_title")
    val detailOriginalTitle: String? = null

    @SerializedName("poster_path")
    val detailPosterPath: String? = null

    @SerializedName("overview")
    val detailOverview: String? = null
}