package com.mertozan.moviescompose.presentation.auth.viewmodel

import com.mertozan.moviescompose.domain.model.UserModel

data class EntryUiState(
    val isLoading: Boolean = false,
    val checkCurrentUser: Boolean = false,
    val exceptionMessage: String = "",
    var rowCount: Int = 0,
    val userItem: UserModel? = UserModel()
)
