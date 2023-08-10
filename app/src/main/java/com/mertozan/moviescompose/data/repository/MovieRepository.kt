package com.mertozan.moviescompose.data.repository

import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {

    private val _repoPopularMovies = MutableStateFlow(emptyList<Movie>())
    val repoPopularMovies = _repoPopularMovies.asStateFlow()

    suspend fun getAllPopularMovies() {
        val response = movieService.getPopularMovies()
        _repoPopularMovies.value = response.movieResults
    }

}
