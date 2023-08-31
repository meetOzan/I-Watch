package com.mertozan.moviescompose.presantation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.common.Constants
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.util.enums.MovieOrSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(emptyList<DetailItem>())
    val popularMovies = _popularMovies.asStateFlow()

    private val _popularSeries = MutableStateFlow(emptyList<DetailItem>())
    val popularSeries = _popularSeries.asStateFlow()

    private val _topRatedMovies = MutableStateFlow(emptyList<DetailItem>())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _topRatedSeries = MutableStateFlow(emptyList<DetailItem>())
    val topRatedSeries = _topRatedSeries.asStateFlow()

    private val _topRatedContents = MutableStateFlow(emptyList<DetailItem>())
    val topRatedContents = _topRatedContents.asStateFlow()

    private val _contentListTitle = MutableStateFlow("")
    val contentListTitle = _contentListTitle.asStateFlow()

    private val type = savedStateHandle.get<String>(key = Constants.ARGS_TYPE)

    init {
        getPopularMovies()
        getPopularSeries()
    }

    init {
        getTopRatedMovies()
        getTopRatedSeries()
    }

    fun getContentList() {
        when (type) {
            MovieOrSeries.MOVIE.name -> {
                _topRatedContents.value = topRatedMovies.value
                _contentListTitle.value = MovieOrSeries.MOVIE.name
            }

            MovieOrSeries.SERIES.name -> {
                _topRatedContents.value = topRatedSeries.value
                _contentListTitle.value = MovieOrSeries.SERIES.name
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = movieRepository.getAllPopularLocalMovies()
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch {
            _popularSeries.value = movieRepository.getAllPopularLocalSeries()
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _topRatedMovies.value = movieRepository.getAllTopRatedLocalMovies()
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch {
            _topRatedSeries.value = movieRepository.getAllTopRatedLocalSeries()
        }
    }

    fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateMovieFavorite(movieId = id, isFavorite = isFavorite)
        }
    }

    fun updateSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateSeriesFavorite(seriesId = id, isFavorite = isFavorite)
        }
    }

}
