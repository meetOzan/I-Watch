package com.mertozan.moviescompose.presantation.generate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllContents
import com.mertozan.moviescompose.domain.usecase.UpdateMovieIsInWatch
import com.mertozan.moviescompose.domain.usecase.UpdateTopMovieIsInWatch
import com.mertozan.moviescompose.domain.usecase.UpdateTopSeriesIsInWatch
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(
    private val getAllContents: GetAllContents,
    private val updateTopMovieIsInWatch: UpdateTopMovieIsInWatch,
    private val updateMovieIsInWatch: UpdateMovieIsInWatch,
    private val updateTopSeriesIsInWatch: UpdateTopSeriesIsInWatch,
    private val updateSeriesIsInWatch: UpdateMovieIsInWatch
) : ViewModel() {

    private val _generateUiState = MutableStateFlow(GenerateUiState())
    val generateUiState = _generateUiState.asStateFlow()

    fun onAction(action: GenerateAction) {
        when (action) {
            is GenerateAction.ShuffleList -> shuffleList()
            is GenerateAction.AddToWatchList -> addToWatchList(
                id = action.id,
                isInWatch = action.isInWatchList,
                type = action.type,
                listType = action.listType
            )
        }
    }

    private fun addToWatchList(id: Int, isInWatch: Boolean, listType: String, type: String) {
        when (listType) {
            ListType.POPULAR.name -> when (type) {
                ContentType.SERIES.name -> updateSeriesIsInWatch(id, isInWatch)
                ContentType.MOVIE.name -> updateMovieIsInWatch(id, isInWatch)
            }

            ListType.TOP_RATED.name -> when (type) {
                ContentType.SERIES.name -> updateTopSeriesIsInWatch(id, isInWatch)
                ContentType.MOVIE.name -> updateTopMovieIsInWatch(id, isInWatch)
            }
        }
    }

    private fun getAllContent() {
        viewModelScope.launch(Dispatchers.IO) {
            _generateUiState.value = _generateUiState.value.copy(isLoading = true)
            when (val response = getAllContents()) {
                is NetworkResponse.Error -> {
                    _generateUiState.value.errorMessage = response.error
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }
                is NetworkResponse.Success -> {
                    _generateUiState.value.allContents.addAll(response.data)
                    shuffleList()
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    fun getContents() {
        getAllContent()
        shuffleList()
    }

    private fun shuffleList() {
        _generateUiState.value = _generateUiState.value.copy(
            allContents =
            _generateUiState.value.allContents.shuffled().toMutableList()
        )
    }
}