package com.mertozan.moviescompose.presentation.entry.viewmodel

import com.mertozan.moviescompose.domain.model.UserModel

data class EntryUiState(
    val isLoading: Boolean = false,
    val checkCurrentUser: Boolean = false,
    val exceptionMessage: String = "",
    val rowCount: Int = 0,
    val userItem: UserModel? = UserModel()
)
