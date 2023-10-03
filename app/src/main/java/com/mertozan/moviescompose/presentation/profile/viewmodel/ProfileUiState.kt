package com.mertozan.moviescompose.presentation.profile.viewmodel

import com.mertozan.moviescompose.domain.model.UserModel

data class ProfileUiState(
    val isLoading: Boolean = false,
    var user : UserModel = UserModel(),
    val watchCount : Int = 0,
    var errorMessage : Exception = Exception()
)
