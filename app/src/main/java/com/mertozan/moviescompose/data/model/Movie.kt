package com.mertozan.moviescompose.data.model

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    // Şuan ki kullandığım JSON da iki title ve original title aynı
    @SerializedName("original_title") //
    val originalTitle: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genres")
    // JSON da genres "genres":[{"id":12,"name":"Adventure"}]" olarak tutuluyor
    // Sitesinde genre_ids -> array of integers diyor hangisi olmalı.
    val genres: Genres,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("runtime")
    val runtime : Int,
    @SerializedName("original_language")
    val originalLanguage : String
)