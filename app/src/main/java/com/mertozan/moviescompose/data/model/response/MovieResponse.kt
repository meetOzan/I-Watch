package com.mertozan.moviescompose.data.model.response

import com.google.gson.annotations.SerializedName
import com.mertozan.moviescompose.data.model.Movie

data class MovieResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val movieResults : List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)