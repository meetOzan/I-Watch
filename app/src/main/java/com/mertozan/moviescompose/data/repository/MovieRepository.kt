package com.mertozan.moviescompose.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.mertozan.moviescompose.data.FavoritesDao
import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.mapper.toMoviesToDetailItemList
import com.mertozan.moviescompose.data.mapper.toSeriesDetailItemList
import com.mertozan.moviescompose.data.model.GenresResponse
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.MovieEntity
import com.mertozan.moviescompose.data.model.MovieResponse
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.data.model.SeriesEntity
import com.mertozan.moviescompose.data.model.SeriesResponse
import com.mertozan.moviescompose.domain.model.DetailItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val auth: FirebaseAuth,
    private val roomDao: FavoritesDao,
) {
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

    fun signOut() {
        CoroutineScope(Dispatchers.IO).launch {
            auth.signOut()
        }
    }

    fun getAllLocalMovies(): List<DetailItem> {
        return roomDao.getAllMovies().toMoviesToDetailItemList()
    }

    fun getAllLocalSeries(): List<DetailItem> {
        return roomDao.getAllSeries().toSeriesDetailItemList()
    }

    fun addMoviesToFavorites(movieEntity: MovieEntity) {
        roomDao.addMovieFavorites(movieEntity)
    }

    fun addSeriesToFavorites(seriesEntity: SeriesEntity) {
        roomDao.addSeriesFavorites(seriesEntity)
    }

    fun deleteMoviesFromFavorites(movieEntity: MovieEntity) {
        roomDao.deleteMovie(movieEntity)
    }

    fun deleteSeriesFromFavorites(seriesEntity: SeriesEntity) {
        roomDao.deleteSeries(seriesEntity)
    }
}