package com.mertozan.moviescompose.data.model.response

import com.google.gson.annotations.SerializedName
import com.mertozan.moviescompose.data.model.Series

data class SeriesResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val seriesResults : List<Series>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
