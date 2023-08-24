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

    private val _allContents = MutableStateFlow(emptyList<DetailItem>())
    val allContents = _allContents.asStateFlow()

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

    fun getAllContents() {
        _allContents.value = popularMovies.value.plus(popularSeries.value)
    }

    fun shuffleList() {
        _allContents.value = _allContents.value.shuffled()
    }

}