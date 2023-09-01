package com.mertozan.moviescompose.data.model.dto

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genres>
)
