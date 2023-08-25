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
import com.mertozan.moviescompose.data.model.Genres
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.util.enums.ListType
import com.mertozan.moviescompose.util.enums.MovieOrSeries
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
        when(listType){
            ListType.POPULAR.name -> when (type) {
                MovieOrSeries.SERIES.name -> getSingleSeries(id ?: 0)
                MovieOrSeries.MOVIE.name -> getSingleMovie(id ?: 0)
            }
            ListType.TOP_RATED.name -> when (type) {
                MovieOrSeries.SERIES.name -> getSingleTopSeries(id ?: 0)
                MovieOrSeries.MOVIE.name -> getSingleTopMovies(id ?: 0)
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
                movieRepository.getSingleLocalMovie(movieId = movieId).movieEntityToDetailItem()
        }
    }

    private fun getSingleSeries(seriesId: Int) {
        viewModelScope.launch {
            _movieDetailUiState.value =
                movieRepository.getSingleLocalSeries(seriesId = seriesId).seriesEntityToDetailItem()
        }
    }

    private fun getSingleTopMovies(movieId: Int) {
        viewModelScope.launch {
            _movieDetailUiState.value =
                movieRepository.getSingleTopLocalMovies(movieId).topMovieEntityToDetailItem()
        }
    }

    fun getSingleTopSeries(seriesId: Int) {
        viewModelScope.launch {
            _movieDetailUiState.value =
                movieRepository.getSingleTopLocalSeries(seriesId).topSeriesEntityToDetailItem()
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