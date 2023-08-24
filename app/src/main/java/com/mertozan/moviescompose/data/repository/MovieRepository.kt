package com.mertozan.moviescompose.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.mertozan.moviescompose.data.FavoritesDao
import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.mapper.moviesToDetailItemList
import com.mertozan.moviescompose.data.mapper.seriesToDetailItemList
import com.mertozan.moviescompose.data.mapper.toDetailItemToMovieEntityList
import com.mertozan.moviescompose.data.mapper.toDetailItemToSeriesEntityList
import com.mertozan.moviescompose.data.mapper.toMoviesToDetailItemList
import com.mertozan.moviescompose.data.mapper.toSeriesDetailItemList
import com.mertozan.moviescompose.data.model.GenresResponse
import com.mertozan.moviescompose.data.model.MovieEntity
import com.mertozan.moviescompose.data.model.MovieResponse
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

    init {
        transferToLocal()
    }

    private suspend fun getAllPopularMovies(): MovieResponse {
        return movieService.getPopularMovies()
    }

    private suspend fun getAllPopularSeries(): SeriesResponse {
        return movieService.getPopularSeries()
    }

    suspend fun getMovieGenres(): GenresResponse {
        return movieService.getMovieGenres()
    }

    suspend fun getSeriesGenres(): GenresResponse {
        return movieService.getSeriesGenres()
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

    private fun addMoviesToLocal(movieItem: List<DetailItem>) {
        roomDao.addMovieFavorites(movieItem.toDetailItemToMovieEntityList())
    }

    private fun addSeriesToLocal(seriesItem: List<DetailItem>) {
        roomDao.addSeriesFavorites(seriesItem.toDetailItemToSeriesEntityList())
    }

    fun getSingleLocalMovie(movieId: Int): MovieEntity {
        return roomDao.getSingleLocalMovie(movieId = movieId)
    }

    fun getSingleLocalSeries(seriesId: Int): SeriesEntity {
        return roomDao.getSingleLocalSeries(seriesId = seriesId)
    }

    fun updateMovieFavorite(movieId: Int, isFavorite: Boolean) {
        roomDao.updateMovieFavoriteState(movieId = movieId, isFavorite = !isFavorite)
    }

    fun updateSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        roomDao.updateSeriesFavoriteState(seriesId = seriesId, isFavorite = isFavorite)
    }

    private fun transferToLocal() {
        CoroutineScope(Dispatchers.IO).launch {
            addMoviesToLocal(getAllPopularMovies().movieResults.moviesToDetailItemList())
            addSeriesToLocal(getAllPopularSeries().seriesResults.seriesToDetailItemList())
        }
    }
}