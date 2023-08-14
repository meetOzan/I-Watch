package com.mertozan.moviescompose.data.api

import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.data.model.Genres
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.MovieResponse
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.data.model.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

// Service olarak mı Api olarak mı isimlendirmem lazım ?
interface MovieService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularMovies() : MovieResponse

    @GET("tv/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularSeries() : SeriesResponse

    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieGenres() : Genres

    @GET("genre/tv/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getSeriesGenres() : Genres

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getSingleMovie(
        @Path("movie_id") movieId: Int,
    ) : Movie

    @GET("tv/{series_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getSingleSeries(
        @Path("series_id") seriesId: Int,
    ) : Series

    // GET https://api.themoviedb.org/3/movie/{movie_id} | Single Movie Details
    // GET https://api.themoviedb.org/3/tv/{series_id} | Single TV Series Details

    // GET https://api.themoviedb.org/3/trending/movie/{time_window} | Trend Movies
    // GET https://api.themoviedb.org/3/trending/tv/{time_window} | Trend TV Series

    // GET https://api.themoviedb.org/3/account/{account_id} | Get Account
    // ? -> Retrofitte user mantığı kullanmadım

    // POST https://api.themoviedb.org/3/account/{account_id}/favorite | Add to Fav
    // GET https://api.themoviedb.org/3/account/{account_id}/favorite/movies | Get Fav Movies
    // GET https://api.themoviedb.org/3/account/{account_id}/favorite/tv | Get Fav TV Series

}