package com.mertozan.moviescompose.presentation.detail.viewmodel

sealed class DetailAction {
    data object GetSingleDetail : DetailAction()
    data class UpdateSingleFavorite(val isFavorite: Boolean) : DetailAction()
}
