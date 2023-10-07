package com.mertozan.moviescompose.presentation.profile.viewmodel

import com.mertozan.moviescompose.domain.model.UserModel

sealed class ProfileAction {
    data class SignOut(val userModel: UserModel) : ProfileAction()
}