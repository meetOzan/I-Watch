package com.mertozan.moviescompose.presantation.home.viewmodel

import com.mertozan.moviescompose.domain.model.ContentModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val popularMovies: List<ContentModel> = emptyList(),
    val popularSeries: List<ContentModel> = emptyList(),
    var topRatedMovies: List<ContentModel> = emptyList(),
    val topRatedSeries: List<ContentModel> = emptyList(),
    val contentList: List<ContentModel> = emptyList(),
    var contentListType: String = "",
    var contentTitle: String = "",
    val errorMessage: Exception = java.lang.Exception()
)
