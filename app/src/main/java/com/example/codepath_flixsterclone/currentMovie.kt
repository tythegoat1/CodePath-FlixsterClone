package com.example.codepath_flixsterclone

import com.google.gson.annotations.SerializedName


class CurrentMovie {
    @SerializedName("title")
    var movieTitleModel : String? = null

    @SerializedName("overview")
    var movieDescriptionModel : String? = null

    @SerializedName("poster_path")
    var movieImageModel: String? = null
}