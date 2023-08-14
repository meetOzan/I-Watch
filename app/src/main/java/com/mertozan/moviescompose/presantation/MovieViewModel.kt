package com.mertozan.moviescompose.presantation

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
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(emptyList<Movie>())
    val popularMovies = _popularMovies.asStateFlow()

    private val _popularSeries = MutableStateFlow(emptyList<Series>())
    val popularSeries = _popularSeries.asStateFlow()

    init {
        getPopularMovies()
        getPopularSeries()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            val response = movieRepository.getAllPopularMovies()
            _popularMovies.value = response.movieResults
        }
    }

    private fun getPopularSeries(){
        viewModelScope.launch {
            val response = movieRepository.getAllPopularSeries()
            _popularSeries.value = response.seriesResults
        }
    }
}