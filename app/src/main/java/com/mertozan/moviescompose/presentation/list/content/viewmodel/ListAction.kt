package com.mertozan.moviescompose.presentation.list.content.viewmodel

sealed class ListAction {
    data class UpdateFavoriteState(val id: Int, val isFavorite: Boolean, val type: String) :
        ListAction()
    data object GetAllFavoriteContents : ListAction()
    data object GetAllContents : ListAction()
}