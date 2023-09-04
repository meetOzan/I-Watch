package com.mertozan.moviescompose.domain.repository

import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.model.dto.MovieResponse
import com.mertozan.moviescompose.data.model.dto.SeriesResponse
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity

interface ContentRepository {

    fun getAllPopularMovies(): List<MovieEntity>

    fun getAllPopularSeries(): List<SeriesEntity>

    fun getAllTopRatedMovies(): List<TopMovieEntity>

    fun getAllTopRatedSeries(): List<TopSeriesEntity>

    fun addPopularMoviesToLocal(movieItem: List<MovieEntity>)

    fun addPopularSeriesToLocal(seriesItem: List<SeriesEntity>)

    fun addTopRatedMoviesToLocal(movieItem: List<TopMovieEntity>)

    fun addTopRatedSeries(seriesItem: List<TopSeriesEntity>)

    fun getSingleMovie(movieId: Int): MovieEntity

    fun getSingleSeries(seriesId: Int): SeriesEntity

    fun getSingleTopMovies(movieId: Int): TopMovieEntity

    fun getSingleTopSeries(seriesId: Int): TopSeriesEntity

    fun getFavoriteTopMovies() : List<TopMovieEntity>

    fun getFavoritePopularMovies() : List<MovieEntity>

    fun getFavoriteTopSeries() : List<TopSeriesEntity>

    fun getFavoritePopularSeries() : List<SeriesEntity>

    fun updateMovieFavorite(movieId: Int, isFavorite: Boolean)

    fun updateSeriesFavorite(seriesId: Int, isFavorite: Boolean)

    fun updateTopMovieFavorite(movieId: Int, isFavorite: Boolean)

    fun updateTopSeriesFavorite(seriesId: Int, isFavorite: Boolean)

    suspend fun getAllPopularNetworkMovies(): MovieResponse

    suspend fun getAllPopularNetworkSeries(): SeriesResponse

    suspend fun getAllTopRatedNetworkMovies(): MovieResponse

    suspend fun getAllNetworkTopRatedSeries(): SeriesResponse

    suspend fun getMovieGenres(): GenresResponse

    suspend fun getSeriesGenres(): GenresResponse
}