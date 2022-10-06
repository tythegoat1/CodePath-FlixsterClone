package com.example.codepath_flixsterclone

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PosterData(
    @SerialName("results")
    val results: BaseResults?
)

@Keep
@Serializable
data class BaseResults(
    @SerialName("poster_path")
    val posterDataPath: String?
)