package com.mertozan.moviescompose.presantation.generate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllPopularMovies
import com.mertozan.moviescompose.domain.usecase.GetAllPopularSeries
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedMovies
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(
    private val getAllPopularMovies: GetAllPopularMovies,
    private val getAllPopularSeries: GetAllPopularSeries,
    private val getAllTopRatedMovies: GetAllTopRatedMovies,
    private val getAllTopRatedSeries: GetAllTopRatedSeries
) : ViewModel() {

    private val _generateUiState = MutableStateFlow(GenerateUiState())
    val generateUiState = _generateUiState.asStateFlow()

    fun onAction(action: GenerateAction) {
        when (action) {
            GenerateAction.ShuffleList -> shuffleList()
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _generateUiState.value = _generateUiState.value.copy(isLoading = true)
            when (val response = getAllPopularMovies()) {
                is NetworkResponse.Error -> {
                    _generateUiState.value.errorMessage = response.error
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _generateUiState.value.allContents.addAll(response.data)
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _generateUiState.value = _generateUiState.value.copy(isLoading = true)
            when (val response = getAllPopularSeries()) {
                is NetworkResponse.Error -> {
                    _generateUiState.value.errorMessage = response.error
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _generateUiState.value.allContents.addAll(response.data)
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _generateUiState.value = _generateUiState.value.copy(isLoading = true)
            when (val response = getAllTopRatedMovies()) {
                is NetworkResponse.Error -> {
                    _generateUiState.value.errorMessage = response.error
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _generateUiState.value.allContents.addAll(response.data)
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _generateUiState.value = _generateUiState.value.copy(isLoading = true)
            when (val response = getAllTopRatedSeries()) {
                is NetworkResponse.Error -> {
                    _generateUiState.value.errorMessage = response.error
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _generateUiState.value.allContents.addAll(response.data)
                    _generateUiState.value = _generateUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    fun getAllContents() {
        getTopRatedMovies()
        getTopRatedSeries()
        getPopularSeries()
        getPopularMovies()
    }

    private fun shuffleList() {
        _generateUiState.value = _generateUiState.value.copy(
            allContents =
            _generateUiState.value.allContents.shuffled().toMutableList()
        )
    }
}