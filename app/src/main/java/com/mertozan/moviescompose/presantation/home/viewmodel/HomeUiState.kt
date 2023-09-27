package com.mertozan.moviescompose.presantation.home.viewmodel

import com.mertozan.moviescompose.domain.model.ContentModel

data class HomeUiState(
    val popularMovieIsLoading: Boolean = false,
    val popularSeriesIsLoading: Boolean = false,
    val topMoviesIsLoading: Boolean = false,
    val topSeriesIsLoading: Boolean = false,
    val popularMovies: List<ContentModel> = emptyList(),
    val popularSeries: List<ContentModel> = emptyList(),
    var topRatedMovies: List<ContentModel> = emptyList(),
    val topRatedSeries: List<ContentModel> = emptyList(),
    val errorMessage: Exception = java.lang.Exception()
)
