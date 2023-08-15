package com.mertozan.moviescompose.presantation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.mapper.toMovieItem
import com.mertozan.moviescompose.data.mapper.toSeriesItem
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.presantation.DataTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _singleDetail = MutableStateFlow(DetailItem())
    val singleDetail= _singleDetail.asStateFlow()

    init {
        getGenres()
    }

    fun getList(type:String, id:Int){
        when(type){
            DataTypes.SERIES.name-> getSingleSeries(id)
            DataTypes.MOVIE.name -> getSingleMovie(id)
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            movieRepository.getMovieGenres()
            movieRepository.getSeriesGenres()
        }
    }

    private fun getSingleMovie(movieId: Int) {
        viewModelScope.launch {
            val movie = movieRepository.getSingleMovie(movieId = movieId)
            _singleDetail.value = movie.toMovieItem()
        }
    }

    private fun getSingleSeries(seriesId: Int) {
        viewModelScope.launch {
            val series = movieRepository.getSingleSeries(seriesId = seriesId)
            _singleDetail.value = series.toSeriesItem()
        }
    }

}