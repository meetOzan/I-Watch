package com.mertozan.moviescompose.presantation

import androidx.lifecycle.ViewModel
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(emptyList<Movie>())
    val popularMovies = _popularMovies.asStateFlow()

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.getAllPopularMovies()
            _popularMovies.value = movieRepository.repoPopularMovies.value
        }
    }

}