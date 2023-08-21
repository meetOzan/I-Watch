package com.mertozan.moviescompose.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genres")
    val genres: List<Genres>,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("runtime")
    val runtime : Int?,
    @SerializedName("original_language")
    val originalLanguage : String
)