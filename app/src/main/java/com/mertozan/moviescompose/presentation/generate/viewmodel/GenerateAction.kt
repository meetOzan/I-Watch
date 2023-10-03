package com.mertozan.moviescompose.presentation.generate.viewmodel

sealed class GenerateAction {
    data object ShuffleList : GenerateAction()
    data class AddToWatchList(
        val id: Int,
        val isInWatchList: Boolean,
        val type: String,
        val listType: String
    ) : GenerateAction()
}