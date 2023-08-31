package com.mertozan.moviescompose.presantation.home

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.common.Constants
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.util.enums.MovieOrSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(emptyList<DetailItem>())
    val popularMovies = _popularMovies.asStateFlow()

    private val _popularSeries = MutableStateFlow(emptyList<DetailItem>())
    val popularSeries = _popularSeries.asStateFlow()

    private val _topRatedMovies = MutableStateFlow(emptyList<DetailItem>())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _topRatedSeries = MutableStateFlow(emptyList<DetailItem>())
    val topRatedSeries = _topRatedSeries.asStateFlow()

    private val _topRatedContents = MutableStateFlow(emptyList<DetailItem>())
    val topRatedContents = _topRatedContents.asStateFlow()

    private val _contentListType = MutableStateFlow("")
    val contentListType = _contentListType.asStateFlow()

    private val _contentTitle = MutableStateFlow("")
    val contentTitle = _contentTitle.asStateFlow()

    private val type = savedStateHandle.get<String>(key = Constants.ARGS_TYPE)

    init {
        getPopularMovies()
        getPopularSeries()
        getTopRatedMovies()
        getTopRatedSeries()
    }

    fun getContentList() {
        when (type) {
            MovieOrSeries.MOVIE.name -> {
                _topRatedContents.value = topRatedMovies.value
                _contentListType.value = MovieOrSeries.MOVIE.name
                _contentTitle.value = context.getString(R.string.top_rated_movies)
            }

            MovieOrSeries.SERIES.name -> {
                _topRatedContents.value = topRatedSeries.value
                _contentListType.value = MovieOrSeries.SERIES.name
                _contentTitle.value = context.getString(R.string.top_rated_series)
            }
        }
    }

    fun updateFavoriteState(id: Int, isFavorite: Boolean,type: String) {
        when(type){
            MovieOrSeries.MOVIE.name -> updateMovieFavorite(id, isFavorite)
            MovieOrSeries.SERIES.name -> updateSeriesFavorite(id,isFavorite)
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = movieRepository.getAllPopularMovies()
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch {
            _popularSeries.value = movieRepository.getAllPopularSeries()
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _topRatedMovies.value = movieRepository.getAllTopRatedMovies()
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch {
            _topRatedSeries.value = movieRepository.getAllTopRatedSeries()
        }
    }

    private fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateMovieFavorite(movieId = id, isFavorite = isFavorite)
        }
    }

    private fun updateSeriesFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            movieRepository.updateSeriesFavorite(seriesId = id, isFavorite = isFavorite)
        }
    }

}
