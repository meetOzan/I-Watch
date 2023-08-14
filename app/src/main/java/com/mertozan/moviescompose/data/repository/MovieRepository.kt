package com.mertozan.moviescompose.data.repository

import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.model.MovieResponse
import com.mertozan.moviescompose.data.model.SeriesResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getAllPopularMovies(): MovieResponse {
        return movieService.getPopularMovies()
    }

    suspend fun getAllPopularSeries() : SeriesResponse{
        return movieService.getPopularSeries()
    }

}
