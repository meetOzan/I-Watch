package com.mertozan.moviescompose.presentation.home.viewmodel

sealed class HomeAction {
    data class UpdateFavoriteState(val id: Int, val isFavorite: Boolean, val type: String) : HomeAction()
}