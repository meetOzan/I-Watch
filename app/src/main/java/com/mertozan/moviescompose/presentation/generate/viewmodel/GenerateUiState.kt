package com.mertozan.moviescompose.presentation.generate.viewmodel

import com.mertozan.moviescompose.domain.model.ContentModel

data class GenerateUiState(
    val isLoading: Boolean = false,
    var allContents : MutableList<ContentModel> = mutableListOf(),
    var errorMessage: Exception = java.lang.Exception()
)
