package com.mertozan.moviescompose.presantation.list.viewmodel

import com.mertozan.moviescompose.domain.model.ContentModel

data class ListUiState (
    val isLoading: Boolean = false,
    var topRatedMovies: List<ContentModel> = emptyList(),
    val topRatedSeries: List<ContentModel> = emptyList(),
    val favoriteContents: List<ContentModel> = emptyList(),
    val contentList: List<ContentModel> = emptyList(),
    var contentListType: String = "",
    var contentTitle: String = "",
    val errorMessage: Exception = java.lang.Exception()
)