package com.mertozan.moviescompose.data.remote.service

import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.model.dto.Series
import com.mertozan.moviescompose.data.model.dto.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesService{
    @GET("tv/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularSeries(): SeriesResponse

    @GET("genre/tv/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getSeriesGenres(): GenresResponse

    @GET("tv/{series_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getSingleSeries(
        @Path("series_id") seriesId: Int,
    ): Series

    @GET("tv/top_rated?api_key=${BuildConfig.API_KEY}")
    suspend fun getTopRatedSeries(): SeriesResponse

}