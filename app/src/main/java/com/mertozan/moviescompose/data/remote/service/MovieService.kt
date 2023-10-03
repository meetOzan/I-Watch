package com.mertozan.moviescompose.data.remote.service

import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.model.dto.Movie
import com.mertozan.moviescompose.data.model.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularMovies(): MovieResponse

    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieGenres(): GenresResponse

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getSingleMovie(
        @Path("movie_id") movieId: Int,
    ): Movie

    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}")
    suspend fun getTopRatedMovies(): MovieResponse
}