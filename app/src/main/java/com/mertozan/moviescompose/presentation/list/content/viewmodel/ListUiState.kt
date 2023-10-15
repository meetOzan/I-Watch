package com.mertozan.moviescompose.presentation.list.content.viewmodel

import com.mertozan.moviescompose.domain.model.ContentModel

data class ListUiState(
    val favoriteIsLoading: Boolean = false,
    var topRatedMovies: List<ContentModel> = emptyList(),
    val topRatedSeries: List<ContentModel> = emptyList(),
    val favoriteContents: List<ContentModel> = emptyList(),
    val contentList: List<ContentModel> = emptyList(),
    var contentListType: String = "",
    var contentTitle: String = "",
    val errorMessage: Exception = java.lang.Exception()
)