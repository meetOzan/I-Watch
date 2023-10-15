package com.mertozan.moviescompose.presentation.list.watch.viewmodel

import com.mertozan.moviescompose.domain.model.ContentModel

data class WatchListUiState(
    val isWatchedIsLoading: Boolean = false,
    val isInWatchedIsLoading: Boolean = false,
    val isWatchedErrorMessage: Exception = java.lang.Exception(),
    val isInWatchedErrorMessage: Exception = java.lang.Exception(),
    val isWatchedList: List<ContentModel> = emptyList(),
    val isInWatchedList: List<ContentModel> = emptyList()
)