package com.mertozan.moviescompose.presantation.detail.viewmodel

sealed class DetailAction {
    data object GetSingleDetail : DetailAction()
    data class UpdateSingleFavorite(val isFavorite: Boolean) : DetailAction()
}