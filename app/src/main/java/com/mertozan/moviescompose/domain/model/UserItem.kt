package com.mertozan.moviescompose.domain.model

data class UserItem(
    var name: String = "",
    var surname: String = "",
    var signInEmail: String = "",
    var signInPassword: String = "",
    var signUpEmail: String = "",
    var signUpPassword: String = "",
)
