package com.mertozan.moviescompose.presantation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _singleMovie = MutableStateFlow<Movie?>(null)
    val singleMovie = _singleMovie.asStateFlow()

    private val _singleSeries = MutableStateFlow<Series?>(null)
    val singleSeries = _singleSeries.asStateFlow()

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            movieRepository.getMovieGenres()
            movieRepository.getSeriesGenres()
        }
    }

    fun getSingleMovie(movieId: Int) {
        viewModelScope.launch {
            _singleMovie.value = movieRepository.getSingleMovie(movieId = movieId)
        }
    }

    fun getSingleSeries(seriesId: Int) {
        viewModelScope.launch {
            _singleSeries.value = movieRepository.getSingleSeries(seriesId = seriesId)
        }
    }

}