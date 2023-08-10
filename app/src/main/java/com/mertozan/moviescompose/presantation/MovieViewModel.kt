package com.mertozan.moviescompose.presantation

import androidx.lifecycle.ViewModel
import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieService: MovieService
): ViewModel() {

    private val _popularMovies= MutableStateFlow(emptyList<Movie>())
    val popularMovies = _popularMovies.asStateFlow()

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = movieService.getPopularMovies()
            _popularMovies.value = response.movieResults
        }
    }

}