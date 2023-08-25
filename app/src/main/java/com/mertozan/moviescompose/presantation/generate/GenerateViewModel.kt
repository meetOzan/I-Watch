package com.mertozan.moviescompose.presantation.generate

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
class GenerateViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _popularMovies = MutableStateFlow(emptyList<DetailItem>())
    private val popularMovies = _popularMovies.asStateFlow()

    private var _popularSeries = MutableStateFlow(emptyList<DetailItem>())
    private val popularSeries = _popularSeries.asStateFlow()

    private val _topRatedMovies = MutableStateFlow(emptyList<DetailItem>())
    private val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _topRatedSeries = MutableStateFlow(emptyList<DetailItem>())
    private val topRatedSeries = _topRatedSeries.asStateFlow()

    private val _allContents = MutableStateFlow(emptyList<DetailItem>())
    val allContents = _allContents.asStateFlow()

    init {
        getPopularMovies()
        getPopularSeries()
        getTopRatedMovies()
        getTopRatedSeries()
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

    fun getAllContents() {
        _allContents.value =
            popularMovies.value
                .plus(popularSeries.value)
                .plus(topRatedMovies.value)
                .plus(topRatedSeries.value)
    }

    fun shuffleList() {
        _allContents.value = _allContents.value.shuffled()
    }

}