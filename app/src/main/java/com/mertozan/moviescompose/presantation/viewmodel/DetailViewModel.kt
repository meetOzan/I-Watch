package com.mertozan.moviescompose.presantation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.common.Constants.ARGS_ID
import com.mertozan.moviescompose.common.Constants.ARGS_TYPE
import com.mertozan.moviescompose.data.mapper.toMovieItem
import com.mertozan.moviescompose.data.mapper.toSeriesItem
import com.mertozan.moviescompose.data.model.Genres
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.presantation.MovieOrSeries
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

    init {
        viewModelScope.launch {
            getGenres()
        }
    }

    fun getList() {
        when (type) {
            MovieOrSeries.SERIES.name -> getSingleSeries(id ?: 0)
            MovieOrSeries.MOVIE.name -> getSingleMovie(id ?: 0)
        }
    }

    private suspend fun getGenres() {
        movieRepository.getMovieGenres()
        movieRepository.getSeriesGenres()
    }

    private fun getSingleMovie(movieId: Int) {
        viewModelScope.launch {
            val movie = movieRepository.getSingleMovie(movieId = movieId)
            _movieDetailUiState.value = movie.toMovieItem()
        }
    }

    private fun getSingleSeries(seriesId: Int) {
        viewModelScope.launch {
            val series = movieRepository.getSingleSeries(seriesId = seriesId)
            _movieDetailUiState.value = series.toSeriesItem()
        }
    }

    fun getGenres(type: String) {
        viewModelScope.launch {
            val movieGenresResponse = movieRepository.getMovieGenres()
            val seriesGenresResponse = movieRepository.getSeriesGenres()
            when (type) {
                MovieOrSeries.SERIES.name -> _genres.value = seriesGenresResponse.genres
                MovieOrSeries.MOVIE.name -> _genres.value = movieGenresResponse.genres
            }
        }
    }
}