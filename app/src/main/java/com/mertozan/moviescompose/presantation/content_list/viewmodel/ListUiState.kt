package com.mertozan.moviescompose.presantation.content_list.viewmodel

import com.mertozan.moviescompose.domain.model.ContentModel

data class ListUiState (
    val favoriteIsLoading: Boolean = false,
    val watchIsLoading: Boolean = false,
    val watchGoalIsLoading: Boolean = false,
    var topRatedMovies: List<ContentModel> = emptyList(),
    val topRatedSeries: List<ContentModel> = emptyList(),
    val favoriteContents: List<ContentModel> = emptyList(),
    val contentList: List<ContentModel> = emptyList(),
    var contentListType: String = "",
    var contentTitle: String = "",
    val errorMessage: Exception = java.lang.Exception()
)