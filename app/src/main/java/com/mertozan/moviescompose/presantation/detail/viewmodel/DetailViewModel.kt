package com.mertozan.moviescompose.presantation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetMoviesGenres
import com.mertozan.moviescompose.domain.usecase.GetSeriesGenres
import com.mertozan.moviescompose.domain.usecase.GetSinglePopularMovie
import com.mertozan.moviescompose.domain.usecase.GetSinglePopularSeries
import com.mertozan.moviescompose.domain.usecase.GetSingleTopMovie
import com.mertozan.moviescompose.domain.usecase.GetSingleTopSeries
import com.mertozan.moviescompose.domain.usecase.UpdateMovieFavorite
import com.mertozan.moviescompose.domain.usecase.UpdateSeriesFavorite
import com.mertozan.moviescompose.domain.usecase.UpdateTopMovieFavorite
import com.mertozan.moviescompose.domain.usecase.UpdateTopSeriesFavorite
import com.mertozan.moviescompose.presantation.navigation.ARGS_ID
import com.mertozan.moviescompose.presantation.navigation.ARGS_CONTENT_TYPE
import com.mertozan.moviescompose.presantation.navigation.ARGS_TYPE
import com.mertozan.moviescompose.util.enums.ContentTypes
import com.mertozan.moviescompose.util.enums.ListType
import com.mertozan.moviescompose.util.extensions.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSinglePopularMovie: GetSinglePopularMovie,
    private val getSinglePopularSeries: GetSinglePopularSeries,
    private val getSingleTopMovie: GetSingleTopMovie,
    private val getSingleTopSerie: GetSingleTopSeries,
    private val getMoviesGenres: GetMoviesGenres,
    private val getSeriesGenres: GetSeriesGenres,
    private val updatePopularMovieFavorite: UpdateMovieFavorite,
    private val updateTopMoviesFavorite: UpdateTopMovieFavorite,
    private val updatePopularSeriesFavorite: UpdateSeriesFavorite,
    private val updateTopSeriesFavorite: UpdateTopSeriesFavorite,
) : ViewModel() {

    private var _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    private val id = savedStateHandle.get<Int>(key = ARGS_ID)
    private val type = savedStateHandle.get<String>(key = ARGS_TYPE)
    private val listType = savedStateHandle.get<String>(key = ARGS_CONTENT_TYPE)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getGenres()
        }
    }

    fun onAction(action: DetailAction) {
        when (action) {
            DetailAction.GetSingleDetail -> getDetail()
            is DetailAction.UpdateSingleFavorite -> updateFavorite(action.isFavorite)
        }
    }

    private fun getDetail() {
        when (listType) {
            ListType.POPULAR.name -> when (type) {
                ContentTypes.SERIES.name -> getSingleSeries(id.orZero())
                ContentTypes.MOVIE.name -> getSingleMovie(id.orZero())
            }

            ListType.TOP_RATED.name -> when (type) {
                ContentTypes.SERIES.name -> getSingleTopSeries(id.orZero())
                ContentTypes.MOVIE.name -> getSingleTopMovies(id.orZero())
            }
        }
    }

    private fun updateFavorite(isFavorite: Boolean) {
        when (listType) {
            ListType.POPULAR.name -> when (type) {
                ContentTypes.SERIES.name -> updateSeriesFavorite(id.orZero(), isFavorite)
                ContentTypes.MOVIE.name -> updateMovieFavorite(id.orZero(), isFavorite)
            }

            ListType.TOP_RATED.name -> when (type) {
                ContentTypes.SERIES.name -> updateTopSeriesFavorite(id.orZero(), isFavorite)
                ContentTypes.MOVIE.name -> updateTopMovieFavorite(id.orZero(), isFavorite)
            }
        }
    }

    private suspend fun getGenres() {
        getMoviesGenres()
        getSeriesGenres()
    }

    private fun getSingleMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response = getSinglePopularMovie(movieId)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value.movieDetailUiState = response.data
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getSingleSeries(seriesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response = getSinglePopularSeries(seriesId = seriesId)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value.movieDetailUiState = response.data
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getSingleTopMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response = getSingleTopMovie(movieId)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value.movieDetailUiState = response.data
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getSingleTopSeries(seriesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response = getSingleTopSerie(seriesId)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value.movieDetailUiState = response.data
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response =
                updatePopularMovieFavorite(movieId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun updateSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response =
                updatePopularSeriesFavorite(seriesId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun updateTopSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response =
                updateTopSeriesFavorite(seriesId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun updateTopMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val response =
                updateTopMoviesFavorite(movieId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _uiState.value.movieDetailError = response.error
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }
}