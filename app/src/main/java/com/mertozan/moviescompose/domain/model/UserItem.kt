package com.mertozan.moviescompose.domain.model

data class UserItem(
    val id: Int = 1,
    var name: String = "",
    var surname: String = "",
    var fullName : String = "",
    var signInEmail: String = "",
    var signInPassword: String = "",
    var signUpEmail: String = "",
    var signUpPassword: String = "",
    val watched : Int = 0
)
