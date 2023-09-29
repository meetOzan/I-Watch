package com.mertozan.moviescompose.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllPopularMovies
import com.mertozan.moviescompose.domain.usecase.GetAllPopularSeries
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedMovies
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedSeries
import com.mertozan.moviescompose.domain.usecase.UpdateMovieFavorite
import com.mertozan.moviescompose.domain.usecase.UpdateSeriesFavorite
import com.mertozan.moviescompose.util.enums.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPopularMovies: GetAllPopularMovies,
    private val getAllPopularSeries: GetAllPopularSeries,
    private val getAllTopRatedMovies: GetAllTopRatedMovies,
    private val getAllTopRatedSeries: GetAllTopRatedSeries,
    private val updatePopularMovieFavorite: UpdateMovieFavorite,
    private val updatePopularSeriesFavorite: UpdateSeriesFavorite
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.UpdateFavoriteState ->
                updateFavoriteState(action.id, action.isFavorite, action.type)
        }
    }

    init {
        getPopularMovies()
        getPopularSeries()
        getTopRatedMovies()
        getTopRatedSeries()
    }

    private fun updateFavoriteState(id: Int, isFavorite: Boolean, type: String) {
        when (type) {
            ContentType.MOVIE.name -> updateMovieFavorite(id, isFavorite)
            ContentType.SERIES.name -> updateSeriesFavorite(id, isFavorite)
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(popularMovieIsLoading = true)
            getAllPopularMovies()
            when (val response = getAllPopularMovies()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(popularMovieIsLoading = false)
                    delay(500)
                    return@launch
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(popularMovies = response.data)
                    _homeUiState.value = _homeUiState.value.copy(popularMovieIsLoading = false)
                }
            }
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(popularSeriesIsLoading = true)
            getAllPopularSeries()
            when (val response = getAllPopularSeries()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(popularSeriesIsLoading = false)
                    delay(500)
                    return@launch
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(popularSeries = response.data)
                    _homeUiState.value = _homeUiState.value.copy(popularSeriesIsLoading = false)
                }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(topMoviesIsLoading = true)
            getAllTopRatedMovies()
            when (val response = getAllTopRatedMovies()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(topMoviesIsLoading = false)
                    delay(500)
                    return@launch
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(topRatedMovies = response.data)
                    _homeUiState.value = _homeUiState.value.copy(topMoviesIsLoading = false)
                }
            }
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(topSeriesIsLoading = true)
            getAllTopRatedSeries()
            when (val response = getAllTopRatedSeries()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(topSeriesIsLoading = false)
                    delay(500)
                    return@launch
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(topRatedSeries = response.data)
                    _homeUiState.value = _homeUiState.value.copy(topSeriesIsLoading = false)
                }
            }
        }
    }

    private fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response =
                updatePopularMovieFavorite(movieId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                }

                is NetworkResponse.Success -> {
                }
            }
        }
    }

    private fun updateSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response =
                updatePopularSeriesFavorite(seriesId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                }

                is NetworkResponse.Success -> {
                }
            }
        }
    }
}
