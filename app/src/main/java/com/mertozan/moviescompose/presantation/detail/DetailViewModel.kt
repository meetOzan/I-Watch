package com.mertozan.moviescompose.presantation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.common.Constants.ARGS_ID
import com.mertozan.moviescompose.common.Constants.ARGS_LIST_TYPE
import com.mertozan.moviescompose.common.Constants.ARGS_TYPE
import com.mertozan.moviescompose.data.mapper.movieEntityToDetailItem
import com.mertozan.moviescompose.data.mapper.seriesEntityToDetailItem
import com.mertozan.moviescompose.data.mapper.topMovieEntityToDetailItem
import com.mertozan.moviescompose.data.mapper.topSeriesEntityToDetailItem
import com.mertozan.moviescompose.data.model.dto.Genres
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.util.enums.ListType
import com.mertozan.moviescompose.util.enums.MovieOrSeries
import com.mertozan.moviescompose.util.extensions.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailUiState = MutableStateFlow(DetailItem())
    val movieDetailUiState = _movieDetailUiState.asStateFlow()

    private val _genres = MutableStateFlow(emptyList<Genres>())
    val genres = _genres.asStateFlow()

    private val id = savedStateHandle.get<Int>(key = ARGS_ID)
    private val type = savedStateHandle.get<String>(key = ARGS_TYPE)
    private val listType = savedStateHandle.get<String>(key = ARGS_LIST_TYPE)

    init {
        viewModelScope.launch {
            getGenres()
        }
    }

    fun getDetail() {
        when (listType) {
            ListType.POPULAR.name -> when (type) {
                MovieOrSeries.SERIES.name -> getSingleSeries(id.orZero())
                MovieOrSeries.MOVIE.name -> getSingleMovie(id.orZero())
            }
            ListType.TOP_RATED.name -> when (type) {
                MovieOrSeries.SERIES.name -> getSingleTopSeries(id.orZero())
                MovieOrSeries.MOVIE.name -> getSingleTopMovies(id.orZero())
            }
        }
    }

    fun updateFavorite(isFavorite: Boolean) {
        when (listType) {
            ListType.POPULAR.name -> when (type) {
                MovieOrSeries.SERIES.name -> updateSeriesFavorite(id.orZero(), isFavorite)
                MovieOrSeries.MOVIE.name -> updateMovieFavorite(id.orZero(), isFavorite)
            }
            ListType.TOP_RATED.name -> when (type) {
                MovieOrSeries.SERIES.name -> updateTopSeriesFavorite(id.orZero(), isFavorite)
                MovieOrSeries.MOVIE.name -> updateTopMovieFavorite(id.orZero(), isFavorite)
            }
        }
    }

    private suspend fun getGenres() {
        movieRepository.getMovieGenres()
        movieRepository.getSeriesGenres()
    }

    private fun getSingleMovie(movieId: Int) {
        viewModelScope.launch {
            _movieDetailUiState.value =
                movieRepository.getSingleMovie(movieId = movieId).movieEntityToDetailItem()
        }
    }

    private fun getSingleSeries(seriesId: Int) {
        viewModelScope.launch {
            _movieDetailUiState.value =
                movieRepository.getSingleSeries(seriesId = seriesId).seriesEntityToDetailItem()
        }
    }

    private fun getSingleTopMovies(movieId: Int) {
        viewModelScope.launch {
            _movieDetailUiState.value =
                movieRepository.getSingleTopMovies(movieId).topMovieEntityToDetailItem()
        }
    }

    private fun getSingleTopSeries(seriesId: Int) {
        viewModelScope.launch {
            _movieDetailUiState.value =
                movieRepository.getSingleTopSeries(seriesId).topSeriesEntityToDetailItem()
        }
    }

    private fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateMovieFavorite(movieId = id, isFavorite = isFavorite)
        }
    }

    private fun updateSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateSeriesFavorite(seriesId = id, isFavorite = isFavorite)
        }
    }

    private fun updateTopSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateTopSeriesFavorite(seriesId = id, isFavorite = isFavorite)
        }
    }

    private fun updateTopMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateTopMovieFavorite(movieId = id, isFavorite = isFavorite)
        }
    }
}