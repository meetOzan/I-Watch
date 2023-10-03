package com.mertozan.moviescompose.presentation.auth.viewmodel

import com.mertozan.moviescompose.domain.model.UserModel

data class AuthUiState(
    val isLoading: Boolean = false,
    val checkCurrentUser: Boolean = false,
    val exceptionMessage: String = "",
    val userItem: UserModel = UserModel()
)
