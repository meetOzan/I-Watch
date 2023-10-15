package com.mertozan.moviescompose.presentation.home.viewmodel

sealed class HomeAction {
    data class UpdateFavoriteState(val id: Int, val isFavorite: Boolean, val type: String) : HomeAction()
    data object GetAllContents : HomeAction()
    data object GetTopMovies : HomeAction()
    data object GetTopSeries : HomeAction()
    data object GetPopularMovies : HomeAction()
    data object GetPopularSeries : HomeAction()
}