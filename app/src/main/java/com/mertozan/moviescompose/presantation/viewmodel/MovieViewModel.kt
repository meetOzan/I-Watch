package com.mertozan.moviescompose.presantation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.mapper.moviesToList
import com.mertozan.moviescompose.data.mapper.seriesToList
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.presantation.MovieOrSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // SavedStateHandle ile type al

    private val _popularMovies = MutableStateFlow(emptyList<DetailItem>())
    val popularMovies = _popularMovies.asStateFlow()

    private val _popularSeries = MutableStateFlow(emptyList<DetailItem>())
    val popularSeries = _popularSeries.asStateFlow()

    fun getContents(type: String){
        when(type){
            MovieOrSeries.MOVIE.name -> getPopularMovies()
            MovieOrSeries.SERIES.name -> getPopularSeries()
        }
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
}
