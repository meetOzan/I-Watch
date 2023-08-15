package com.mertozan.moviescompose.data.api

import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.data.model.GenresResponse
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.MovieResponse
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.data.model.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularMovies(): MovieResponse

    @GET("tv/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularSeries(): SeriesResponse

    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieGenres(): GenresResponse

    @GET("genre/tv/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getSeriesGenres(): GenresResponse

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getSingleMovie(
        @Path("movie_id") movieId: Int,
    ): Movie

    @GET("tv/{series_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getSingleSeries(
        @Path("series_id") seriesId: Int,
    ): Series

}