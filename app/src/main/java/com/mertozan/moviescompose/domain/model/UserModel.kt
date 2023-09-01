package com.mertozan.moviescompose.domain.model

data class UserModel(
    var name: String = "",
    val surname: String = "",
    val fullName : String = "",
    val signInEmail: String = "",
    val signInPassword: String = "",
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val watched : Int = 0
)
