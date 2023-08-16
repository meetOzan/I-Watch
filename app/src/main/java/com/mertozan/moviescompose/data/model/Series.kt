package com.mertozan.moviescompose.data.model

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genres")
    val genres: List<Genres>,
    @SerializedName("episode_number")
    val episodeNumber: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("overview")
    val overview: String
)
