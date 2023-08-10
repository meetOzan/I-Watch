package com.mertozan.moviescompose.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("status_code")
    val statusCode : Int,
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("status_message")
    val statusMessage : String
)