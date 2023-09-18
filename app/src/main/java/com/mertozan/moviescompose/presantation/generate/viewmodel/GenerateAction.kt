package com.mertozan.moviescompose.presantation.generate.viewmodel

sealed class GenerateAction {
    data object ShuffleList : GenerateAction()
    data class AddToWatchList(
        val id: Int,
        val isInWatchList: Boolean,
        val type: String,
        val listType: String
    ) : GenerateAction()
}