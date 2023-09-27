package com.mertozan.moviescompose.data.source.remote

import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.model.dto.MovieResponse
import com.mertozan.moviescompose.data.model.dto.SeriesResponse
import com.mertozan.moviescompose.data.remote.service.MovieService
import com.mertozan.moviescompose.data.remote.service.SeriesService
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val movieService: MovieService,
    private val seriesService: SeriesService
) {

    suspend fun getAllPopularNetworkMovies(): MovieResponse {
        return movieService.getPopularMovies()
    }

    suspend fun getAllPopularNetworkSeries(): SeriesResponse {
        return seriesService.getPopularSeries()
    }

    suspend fun getAllTopRatedNetworkMovies(): MovieResponse {
        return movieService.getTopRatedMovies()
    }

    suspend fun getAllNetworkTopRatedSeries(): SeriesResponse {
        return seriesService.getTopRatedSeries()
    }

    suspend fun getMovieGenres(): GenresResponse {
        return movieService.getMovieGenres()
    }

    suspend fun getSeriesGenres(): GenresResponse {
        return seriesService.getSeriesGenres()
    }
}