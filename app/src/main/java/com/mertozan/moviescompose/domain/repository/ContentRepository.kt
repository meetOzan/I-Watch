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

    fun getFavoriteTopMovies(): List<TopMovieEntity>

    fun getFavoritePopularMovies(): List<MovieEntity>

    fun getFavoriteTopSeries(): List<TopSeriesEntity>

    fun getFavoritePopularSeries(): List<SeriesEntity>

    fun getAllWatchedPopularMovies(): List<MovieEntity>

    fun getAllWatchedTopMovies(): List<TopMovieEntity>

    fun getAllWatchedPopularSeries(): List<SeriesEntity>

    fun getAllWatchedTopSeries(): List<TopSeriesEntity>

    fun getAllInWatchListPopularMovies(): List<MovieEntity>

    fun getAllInWatchListTopMovies(): List<TopMovieEntity>

    fun getAllInWatchListPopularSeries(): List<SeriesEntity>

    fun getAllInWatchListTopSeries(): List<TopSeriesEntity>

    fun updateMovieFavorite(movieId: Int, isFavorite: Boolean)

    fun updateSeriesFavorite(seriesId: Int, isFavorite: Boolean)

    fun updateTopMovieFavorite(movieId: Int, isFavorite: Boolean)

    fun updateTopSeriesFavorite(seriesId: Int, isFavorite: Boolean)

    fun updatePopularMoviesIsWatched(movieId: Int, isWatched: Boolean)

    fun updatePopularMoviesIsInWatchList(movieId: Int, isInWatched: Boolean)

    fun updatePopularSeriesIsWatched(seriesId: Int, isWatched: Boolean)

    fun updatePopularSeriesIsInWatchedList(seriesId: Int, isInWatched: Boolean)

    fun updateTopMoviesIsWatched(movieId: Int, isWatched: Boolean)

    fun updateTopMoviesIsInWatchList(movieId: Int, isInWatched: Boolean)

    fun updateTopSeriesIsWatched(seriesId: Int, isWatched: Boolean)

    fun updateTopSeriesIsInWatchedList(seriesId: Int, isInWatched: Boolean)

    suspend fun getAllPopularNetworkMovies(): MovieResponse

    suspend fun getAllPopularNetworkSeries(): SeriesResponse

    suspend fun getAllTopRatedNetworkMovies(): MovieResponse

    suspend fun getAllNetworkTopRatedSeries(): SeriesResponse

    suspend fun getMovieGenres(): GenresResponse

    suspend fun getSeriesGenres(): GenresResponse
}