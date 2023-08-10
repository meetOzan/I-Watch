package com.mertozan.moviescompose.data.api

import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.data.model.MovieResponse
import com.mertozan.moviescompose.data.model.SeriesResponse
import retrofit2.http.GET

// Service olarak mı Api olarak mı isimlendirmem lazım ?
interface MovieService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularMovies() : MovieResponse
    @GET("tv/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularSeries() : SeriesResponse

    // GET https://api.themoviedb.org/3/movie/{movie_id} | Single Movie Details
    // GET https://api.themoviedb.org/3/tv/{series_id} | Single TV Series Details


    // GET https://api.themoviedb.org/3/trending/movie/{time_window} | Trend Movies
    // GET https://api.themoviedb.org/3/trending/tv/{time_window} | Trend TV Series

    // GET https://api.themoviedb.org/3/tv/popular | Popular TV Series
    // GET https://api.themoviedb.org/3/movie/popular | Popular Movies
    // GET https://api.themoviedb.org/3/movie/{movie_id}/images | Get Movie Image

    // GET https://api.themoviedb.org/3/account/{account_id} | Get Account
    // ? -> Retrofitte user mantığı kullanmadım

    // POST https://api.themoviedb.org/3/account/{account_id}/favorite | Add to Fav
    // GET https://api.themoviedb.org/3/account/{account_id}/favorite/movies | Get Fav Movies
    // GET https://api.themoviedb.org/3/account/{account_id}/favorite/tv | Get Fav TV Series

}