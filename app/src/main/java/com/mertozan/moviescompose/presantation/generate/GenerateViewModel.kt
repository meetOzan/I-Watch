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

    private val _allContents = MutableStateFlow(mutableListOf<DetailItem>())
    val allContents = _allContents.asStateFlow()

    private fun getPopularMovies() {
        viewModelScope.launch {
            _allContents.value.addAll(movieRepository.getAllPopularLocalMovies())
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch {
            _allContents.value.addAll(movieRepository.getAllPopularLocalSeries())
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _allContents.value.addAll(movieRepository.getAllTopRatedLocalMovies())
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch {
            _allContents.value.addAll(movieRepository.getAllTopRatedLocalSeries())
        }
    }

    fun getAllContents() {
        getTopRatedMovies()
        getTopRatedSeries()
        getPopularSeries()
        getPopularMovies()
    }

    fun shuffleList() {
        _allContents.value = _allContents.value.shuffled() as MutableList<DetailItem>
    }
}