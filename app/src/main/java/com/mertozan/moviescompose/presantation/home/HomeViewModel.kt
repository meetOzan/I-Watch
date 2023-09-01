package com.mertozan.moviescompose.presantation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.usecase.GetAllPopularMovies
import com.mertozan.moviescompose.domain.usecase.GetAllPopularSeries
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedMovies
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedSeries
import com.mertozan.moviescompose.domain.usecase.UpdateMovieFavorite
import com.mertozan.moviescompose.domain.usecase.UpdateSeriesFavorite
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
    private val updatePopularSeriesFavorite: UpdateSeriesFavorite
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(emptyList<ContentModel>())
    val popularMovies = _popularMovies.asStateFlow()

    private val _popularSeries = MutableStateFlow(emptyList<ContentModel>())
    val popularSeries = _popularSeries.asStateFlow()

    private val _topRatedMovies = MutableStateFlow(emptyList<ContentModel>())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _topRatedSeries = MutableStateFlow(emptyList<ContentModel>())
    val topRatedSeries = _topRatedSeries.asStateFlow()

    private val _topRatedContents = MutableStateFlow(emptyList<ContentModel>())
    val topRatedContents = _topRatedContents.asStateFlow()

    private val _contentListType = MutableStateFlow("")
    val contentListType = _contentListType.asStateFlow()

    private val _contentTitle = MutableStateFlow("")
    val contentTitle = _contentTitle.asStateFlow()

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
                _topRatedContents.value = topRatedMovies.value
                _contentListType.value = ContentTypes.MOVIE.name
                _contentTitle.value = "Top Rated Movies"
            }

            ContentTypes.SERIES.name -> {
                _topRatedContents.value = topRatedSeries.value
                _contentListType.value = ContentTypes.SERIES.name
                _contentTitle.value = "Top Rated Series"
            }
        }
    }

    fun updateFavoriteState(id: Int, isFavorite: Boolean, type: String) {
        when (type) {
            ContentTypes.MOVIE.name -> updateMovieFavorite(id, isFavorite)
            ContentTypes.SERIES.name -> updateSeriesFavorite(id, isFavorite)
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovies.value = getAllPopularMovies()
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _popularSeries.value = getAllPopularSeries()
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedMovies.value = getAllTopRatedMovies()
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedSeries.value = getAllTopRatedSeries()
        }
    }

    private fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePopularMovieFavorite(movieId = id, isFavorite = isFavorite)
        }
    }

    private fun updateSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePopularSeriesFavorite(seriesId = id, isFavorite = isFavorite)
        }
    }
}
