package com.mertozan.moviescompose.presantation.home.viewmodel

sealed class HomeAction {
    data class UpdateFavoriteState(val id: Int, val isFavorite: Boolean, val type: String) : HomeAction()
}