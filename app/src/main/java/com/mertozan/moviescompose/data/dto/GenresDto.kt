package com.mertozan.moviescompose.data.dto

import com.google.gson.annotations.SerializedName

data class GenresDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
) {
}