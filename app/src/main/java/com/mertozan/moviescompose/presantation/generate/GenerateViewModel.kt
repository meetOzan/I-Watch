package com.mertozan.moviescompose.presantation.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.usecase.GetAllPopularMovies
import com.mertozan.moviescompose.domain.usecase.GetAllPopularSeries
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedMovies
import com.mertozan.moviescompose.domain.usecase.GetAllTopRatedSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(
    private val getAllPopularMovies: GetAllPopularMovies,
    private val getAllPopularSeries: GetAllPopularSeries,
    private val getAllTopRatedMovies: GetAllTopRatedMovies,
    private val getAllTopRatedSeries: GetAllTopRatedSeries
) : ViewModel() {

    private val _allContents = MutableStateFlow(mutableListOf<ContentModel>())
    val allContents = _allContents.asStateFlow()

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _allContents.value.addAll(getAllPopularMovies())
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _allContents.value.addAll(getAllPopularSeries())
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _allContents.value.addAll(getAllTopRatedMovies())
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            _allContents.value.addAll(getAllTopRatedSeries())
        }
    }

    fun getAllContents() {
        getTopRatedMovies()
        getTopRatedSeries()
        getPopularSeries()
        getPopularMovies()
    }

    fun shuffleList() {
        _allContents.value = _allContents.value.shuffled().toMutableList()
    }
}