package com.mertozan.moviescompose.data.dto

import com.google.gson.annotations.SerializedName

// Fazla olanları silebiliriz
data class SeriesDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val originalName : String,
    @SerializedName("popularity")
    val popularity : Int,
    @SerializedName("first_air_date")
    val firstAirDate : String,
    @SerializedName("genres")
    val genres: GenresDto,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("original_language")
    val originalLanguage : String,
    @SerializedName("overview")
    val overview: String
)
