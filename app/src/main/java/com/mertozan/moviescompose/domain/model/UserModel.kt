package com.mertozan.moviescompose.domain.model

data class UserModel(
    val id : Int = 1,
    var name: String = "",
    val surname: String = "",
    val fullName : String = "",
    val watched : Int = 0,
    val language: String = "en"
)
