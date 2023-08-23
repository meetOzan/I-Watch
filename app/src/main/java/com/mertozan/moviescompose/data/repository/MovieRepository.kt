package com.mertozan.moviescompose.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.model.GenresResponse
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.MovieResponse
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.data.model.SeriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {

    private val auth = Firebase.auth

    suspend fun getAllPopularMovies(): MovieResponse {
        return movieService.getPopularMovies()
    }

    suspend fun getAllPopularSeries(): SeriesResponse {
        return movieService.getPopularSeries()
    }

    suspend fun getMovieGenres(): GenresResponse {
        return movieService.getMovieGenres()
    }

    suspend fun getSeriesGenres(): GenresResponse {
        return movieService.getSeriesGenres()
    }

    suspend fun getSingleMovie(movieId: Int): Movie {
        return movieService.getSingleMovie(movieId = movieId)
    }

    suspend fun getSingleSeries(seriesId: Int): Series {
        return movieService.getSingleSeries(seriesId = seriesId)
    }

    fun firebaseSignOut() {
        CoroutineScope(Dispatchers.IO).launch {
            auth.signOut()
        }
    }
}