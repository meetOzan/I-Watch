package com.mertozan.moviescompose.data.model.dto

data class User(
    val name : String,
    val surname : String,
    val email: String,
    val password: String,
    val watched: Int
)