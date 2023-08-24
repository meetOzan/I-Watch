package com.mertozan.moviescompose.presantation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(emptyList<DetailItem>())
    val popularMovies = _popularMovies.asStateFlow()

    private val _popularSeries = MutableStateFlow(emptyList<DetailItem>())
    val popularSeries = _popularSeries.asStateFlow()

    init {
        getPopularMovies()
        getPopularSeries()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = movieRepository.getAllLocalMovies()
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch {
            _popularSeries.value = movieRepository.getAllLocalSeries()
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
