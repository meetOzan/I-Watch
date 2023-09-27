package com.mertozan.moviescompose.presantation.detail.viewmodel

import com.mertozan.moviescompose.data.model.dto.Genres
import com.mertozan.moviescompose.domain.model.ContentModel

data class DetailUiState(
    val isLoading: Boolean = false,
    var movieDetailUiState : ContentModel = ContentModel(),
    var movieDetailError : Exception = java.lang.Exception(),
    val genres: List<Genres> = emptyList(),
)