package com.mertozan.moviescompose.presantation.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllPopularMovies
import com.mertozan.moviescompose.domain.usecase.GetAllPopularSeries
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedMovies
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedSeries
import com.mertozan.moviescompose.domain.usecase.UpdateMovieFavorite
import com.mertozan.moviescompose.domain.usecase.UpdateSeriesFavorite
import com.mertozan.moviescompose.infrastructure.StringResourceProvider
import com.mertozan.moviescompose.presantation.navigation.ARGS_TYPE
import com.mertozan.moviescompose.util.enums.ContentTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllPopularMovies: GetAllPopularMovies,
    private val getAllPopularSeries: GetAllPopularSeries,
    private val getAllTopRatedMovies: GetAllTopRatedMovies,
    private val getAllTopRatedSeries: GetAllTopRatedSeries,
    private val updatePopularMovieFavorite: UpdateMovieFavorite,
    private val updatePopularSeriesFavorite: UpdateSeriesFavorite,
    private val stringRes: StringResourceProvider
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    fun onAction(action: HomeAction){
        when(action){
            is HomeAction.UpdateFavoriteState ->
                updateFavoriteState(action.id,action.isFavorite,action.type)
        }
    }

    private val type = savedStateHandle.get<String>(key = ARGS_TYPE)

    init {
        getPopularMovies()
        getPopularSeries()
        getTopRatedMovies()
        getTopRatedSeries()
    }

    fun getContentList() {
        when (type) {
            ContentTypes.MOVIE.name -> {
                _homeUiState.value = _homeUiState.value.copy(
                    contentList = _homeUiState.value.topRatedMovies
                )
                _homeUiState.value.contentListType = ContentTypes.MOVIE.name
                _homeUiState.value.contentTitle = stringRes.getString(R.string.top_rated_movies)
            }

            ContentTypes.SERIES.name -> {
                _homeUiState.value = _homeUiState.value.copy(
                    contentList = _homeUiState.value.topRatedSeries
                )
                _homeUiState.value.contentListType = ContentTypes.SERIES.name
                _homeUiState.value.contentTitle = stringRes.getString(R.string.top_rated_series)
            }
        }
    }

    fun updateFavoriteState(id: Int, isFavorite: Boolean, type: String) {
        when (type) {
            ContentTypes.MOVIE.name -> updateMovieFavorite(id,isFavorite)
            ContentTypes.SERIES.name -> updateSeriesFavorite(id, isFavorite)
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(isLoading = true)
            when (val response = getAllPopularMovies()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(popularMovies = response.data)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(isLoading = true)
            when (val response = getAllPopularSeries()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(popularSeries = response.data)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(isLoading = true)
            when (val response = getAllTopRatedMovies()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(topRatedMovies = response.data)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(isLoading = true)
            when (val response = getAllTopRatedSeries()) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(topRatedSeries = response.data)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(isLoading = true)
            when (val response =
                updatePopularMovieFavorite(movieId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun updateSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.value = _homeUiState.value.copy(isLoading = true)
            when (val response =
                updatePopularSeriesFavorite(seriesId = id, isFavorite = isFavorite)) {
                is NetworkResponse.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = response.error)
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(isLoading = false)
                }
            }
        }
    }
}
