package com.mertozan.moviescompose.presantation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.mapper.moviesToList
import com.mertozan.moviescompose.data.mapper.seriesToList
import com.mertozan.moviescompose.data.model.MovieEntity
import com.mertozan.moviescompose.data.model.SeriesEntity
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


    private val _favSeries = MutableStateFlow(emptyList<DetailItem>())
    val favSeries = _favSeries.asStateFlow()

    private val _favMovies = MutableStateFlow(emptyList<DetailItem>())
    val favMovies = _favMovies.asStateFlow()

    init {
        getPopularMovies()
        getPopularSeries()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            val response = movieRepository.getAllPopularMovies()
            _popularMovies.value = response.movieResults.moviesToList()
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch {
            val response = movieRepository.getAllPopularSeries()
            _popularSeries.value = response.seriesResults.seriesToList()
        }
    }

    fun getMoviesFromFavorites() {
        viewModelScope.launch {
            _favMovies.value = movieRepository.getAllLocalMovies()
        }
    }

    fun getSeriesFromFavorites() {
        viewModelScope.launch {
            _favSeries.value = movieRepository.getAllLocalSeries()
        }
    }

    fun addMovieToFavorites(movieEntity: MovieEntity) {
        viewModelScope.launch {
            movieRepository.addMoviesToFavorites(movieEntity)
        }
    }

    fun addSeriesToFavorites(seriesEntity: SeriesEntity) {
        viewModelScope.launch {
            movieRepository.addSeriesToFavorites(seriesEntity)
        }
    }

    fun deleteMoviesFromFavorites(movieEntity: MovieEntity) {
        viewModelScope.launch {
            movieRepository.deleteMoviesFromFavorites(movieEntity)
        }
    }

    fun deleteSeriesFromFavorites(seriesEntity: SeriesEntity) {
        viewModelScope.launch {
            movieRepository.deleteSeriesFromFavorites(seriesEntity)
        }
    }
}
