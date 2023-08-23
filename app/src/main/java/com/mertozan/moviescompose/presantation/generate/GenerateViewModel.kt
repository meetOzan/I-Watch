package com.mertozan.moviescompose.presantation.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.mapper.moviesToList
import com.mertozan.moviescompose.data.mapper.seriesToList
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _popularMovies = MutableStateFlow(emptyList<DetailItem>())
    val popularMovies = _popularMovies.asStateFlow()

    private var _popularSeries = MutableStateFlow(emptyList<DetailItem>())
    val popularSeries = _popularSeries.asStateFlow()

    private val _allContents = MutableStateFlow(emptyList<DetailItem>())
    val allContents = _allContents.asStateFlow()

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

    fun getAllContents() {
        _allContents.value = popularMovies.value.plus(popularSeries.value)
    }

    fun shuffleList() {
        _allContents.value = _allContents.value.shuffled()
    }
}