package com.mertozan.moviescompose.presentation.generate.viewmodel

sealed class GenerateAction {
    data object ShuffledList : GenerateAction()
    data object GetAllContents : GenerateAction()
    data class AddToWatchList(
        val id: Int,
        val isInWatchList: Boolean,
        val type: String,
        val listType: String
    ) : GenerateAction()
}