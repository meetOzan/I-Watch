package com.mertozan.moviescompose.data.repository

import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.model.Genres
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.MovieResponse
import com.mertozan.moviescompose.data.model.Series
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

    suspend fun getMovieGenres() : Genres {
        return movieService.getMovieGenres()
    }

    suspend fun getSeriesGenres() : Genres {
        return movieService.getSeriesGenres()
    }

    suspend fun getSingleMovie(movieId: Int) : Movie {
        return movieService.getSingleMovie(movieId = movieId)
    }

    suspend fun getSingleSeries(seriesId: Int) : Series {
        return movieService.getSingleSeries(seriesId = seriesId)
    }

}
