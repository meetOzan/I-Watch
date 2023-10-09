package com.mertozan.moviescompose.presentation.list.content.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllFavorites
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedMovies
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedSeries
import com.mertozan.moviescompose.infrastructure.provider.StringResourceProvider
import com.mertozan.moviescompose.presentation.navigation.ARGS_TYPE
import com.mertozan.moviescompose.util.enums.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllTopRatedMovies: GetAllTopRatedMovies,
    private val getAllTopRatedSeries: GetAllTopRatedSeries,
    private val getAllFavorites: GetAllFavorites,
    private val stringRes: StringResourceProvider
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState())
    val listUiState = _listUiState.asStateFlow()

    private val type = savedStateHandle.get<String>(key = ARGS_TYPE)

    init {
        getAllFavoriteContents()
        getTopRatedSeries()
        getTopRatedMovies()
    }

    fun getContentList() {
        when (type) {
            ContentType.MOVIE.name -> {
                _listUiState.value = _listUiState.value.copy(
                    contentList = _listUiState.value.topRatedMovies
                )
                _listUiState.value.contentListType = ContentType.MOVIE.name
                _listUiState.value.contentTitle = stringRes.getString(R.string.top_rated_movies)
            }

            ContentType.SERIES.name -> {
                _listUiState.value = _listUiState.value.copy(
                    contentList = _listUiState.value.topRatedSeries
                )
                _listUiState.value.contentListType = ContentType.SERIES.name
                _listUiState.value.contentTitle = stringRes.getString(R.string.top_rated_series)
            }

            ContentType.FAVORITE_CONTENTS.name -> {
                _listUiState.value = _listUiState.value.copy(
                    contentList = _listUiState.value.favoriteContents
                )
                _listUiState.value.contentListType = ContentType.FAVORITE_CONTENTS.name
                _listUiState.value.contentTitle = stringRes.getString(R.string.favorite_contents)
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _listUiState.value = _listUiState.value.copy(favoriteIsLoading = true)
            when (val response = getAllTopRatedMovies()) {
                is NetworkResponse.Error -> {
                    _listUiState.value = _listUiState.value.copy(errorMessage = response.error)
                    _listUiState.value = _listUiState.value.copy(favoriteIsLoading = false)
                }

                is NetworkResponse.Success -> {
                    _listUiState.value = _listUiState.value.copy(topRatedMovies = response.data)
                    _listUiState.value = _listUiState.value.copy(favoriteIsLoading = false)
                }
            }
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _listUiState.value = _listUiState.value.copy(favoriteIsLoading = true)
            when (val response = getAllTopRatedSeries()) {
                is NetworkResponse.Error -> {
                    _listUiState.value = _listUiState.value.copy(errorMessage = response.error)
                    _listUiState.value = _listUiState.value.copy(favoriteIsLoading = false)
                }

                is NetworkResponse.Success -> {
                    _listUiState.value = _listUiState.value.copy(topRatedSeries = response.data)
                    _listUiState.value = _listUiState.value.copy(favoriteIsLoading = false)
                }
            }
        }
    }

    private fun getAllFavoriteContents() {
        viewModelScope.launch(Dispatchers.IO) {
            _listUiState.value = _listUiState.value.copy(favoriteIsLoading = true)
            when (val response = getAllFavorites()) {
                is NetworkResponse.Error -> {
                    _listUiState.value = _listUiState.value.copy(errorMessage = response.error)
                    _listUiState.value = _listUiState.value.copy(favoriteIsLoading = false)
                }

                is NetworkResponse.Success -> {
                    _listUiState.value = _listUiState.value.copy(favoriteContents = response.data)
                    _listUiState.value = _listUiState.value.copy(favoriteIsLoading = false)
                }
            }
        }
    }
}