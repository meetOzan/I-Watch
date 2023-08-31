package com.mertozan.moviescompose.data.model.response

import com.google.gson.annotations.SerializedName
import com.mertozan.moviescompose.data.model.Genres

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genres>
)
