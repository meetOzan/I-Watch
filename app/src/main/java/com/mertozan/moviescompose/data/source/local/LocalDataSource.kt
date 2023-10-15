package com.mertozan.moviescompose.data.source.local

import com.mertozan.moviescompose.data.local.dao.MovieDao
import com.mertozan.moviescompose.data.local.dao.SeriesDao
import com.mertozan.moviescompose.data.local.dao.UserDao
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val userDao: UserDao
) {

    // Get Ops.
    fun getAllPopularMovies(): List<MovieEntity> {
        return movieDao.getPopularMovies()
    }

    fun getAllPopularSeries(): List<SeriesEntity> {
        return seriesDao.getPopularSeries()
    }

    fun getAllTopRatedMovies(): List<TopMovieEntity> {
        return movieDao.getTopMovies()
    }

    fun getAllTopRatedSeries(): List<TopSeriesEntity> {
        return seriesDao.getTopSeries()
    }

    fun addPopularMoviesToLocal(movieItem: List<MovieEntity>) {
        movieDao.addMovieToLocal(movieItem)
    }

    fun addPopularSeriesToLocal(seriesItem: List<SeriesEntity>) {
        seriesDao.addSeriesToLocal(seriesItem)
    }

    fun addTopRatedMoviesToLocal(movieItem: List<TopMovieEntity>) {
        movieDao.addTopMoviesToLocal(movieItem)
    }

    fun addTopRatedSeries(seriesItem: List<TopSeriesEntity>) {
        seriesDao.addTopSeriesToLocal(seriesItem)
    }

    // User Ops.
    fun addUserToLocal(user: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.addUserToLocal(user)
        }
    }

    fun getRowCount(): Int {
        return userDao.getRowCount()
    }

    fun deleteUserFromLocal(user: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteUserFromLocal(user)
        }
    }

    fun getSingleLocalUser(): UserEntity {
        return userDao.getSingleUser()
    }

    fun updateUserWatchState(userWatched: Int) {
        userDao.updateUserWatchState(userWatched = userWatched)
    }

    // Get Favorite List
    fun getFavoritePopularMovie(): List<MovieEntity> {
        return movieDao.getFavoritePopularMovie()
    }

    fun getFavoriteTopMovie(): List<TopMovieEntity> {
        return movieDao.getFavoriteTopMovies()
    }

    fun getFavoritePopularSeries(): List<SeriesEntity> {
        return seriesDao.getFavoritePopularSeries()
    }

    fun getFavoriteTopSeries(): List<TopSeriesEntity> {
        return seriesDao.getFavoriteTopLocalSeries()
    }

    // Get Watch List
    fun getWatchedPopularMovies(): List<MovieEntity> {
        return movieDao.getWatchedPopularMovies()
    }

    fun getInWatchListPopularMovies(): List<MovieEntity> {
        return movieDao.getInWatchListPopularMovies()
    }

    fun getWatchedPopularSeries(): List<SeriesEntity> {
        return seriesDao.getWatchedPopularSeries()
    }

    fun getInWatchListPopularSeries(): List<SeriesEntity> {
        return seriesDao.getInWatchListPopularSeries()
    }

    fun getWatchedTopMovies(): List<TopMovieEntity> {
        return movieDao.getWatchedTopMovies()
    }

    fun getInWatchListTopMovies(): List<TopMovieEntity> {
        return movieDao.getInWatchListTopMovies()
    }

    fun getWatchedTopSeries(): List<TopSeriesEntity> {
        return seriesDao.getWatchedTopSeries()
    }

    fun getInWatchListTopSeries(): List<TopSeriesEntity> {
        return seriesDao.getInWatchListTopSeries()
    }

    fun getSingleMovie(movieId: Int): MovieEntity {
        return movieDao.getSingleMovie(movieId = movieId)
    }

    fun getSingleSeries(seriesId: Int): SeriesEntity {
        return seriesDao.getSinglePopularSeries(seriesId = seriesId)
    }

    fun getSingleTopMovies(movieId: Int): TopMovieEntity {
        return movieDao.getTopSingleMovie(movieId)
    }

    fun getSingleTopSeries(seriesId: Int): TopSeriesEntity {
        return seriesDao.getTopSingleLocalSeries(seriesId)
    }

    // Update Favorite State
    fun updateMovieFavorite(movieId: Int, isFavorite: Boolean) {
        movieDao.updateMovieFavoriteState(movieId = movieId, isFavorite = !isFavorite)
    }

    fun updateSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        seriesDao.updateSeriesFavoriteState(seriesId = seriesId, isFavorite = !isFavorite)
    }

    fun updateTopMovieFavorite(movieId: Int, isFavorite: Boolean) {
        movieDao.updateTopMovieFavoriteState(topMovieId = movieId, isFavorite = !isFavorite)
    }

    fun updateTopSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        seriesDao.updateTopSeriesFavoriteState(topSeriesId = seriesId, isFavorite = !isFavorite)
    }

    // Update Watch State
    fun updateMovieIsWatched(movieId: Int, isWatched: Boolean) {
        movieDao.updateMovieIsWatchedState(movieId = movieId, isWatched = !isWatched)
    }

    fun updateMovieIsInWatchedList(movieId: Int, isInWatched: Boolean) {
        movieDao.updateMovieInWatchListState(movieId = movieId, isInWatch = !isInWatched)
    }

    fun updateSeriesIsWatched(seriesId: Int, isWatched: Boolean) {
        seriesDao.updateSeriesIsWatchedState(seriesId = seriesId, isWatched = !isWatched)
    }

    fun updateSeriesIsInWatchedList(seriesId: Int, isInWatched: Boolean) {
        seriesDao.updateSeriesInWatchListState(seriesId = seriesId, isInWatch = !isInWatched)
    }

    fun updateTopMovieIsWatched(movieId: Int, isWatched: Boolean) {
        movieDao.updateTopMovieIsWatchedState(movieId = movieId, isWatched = !isWatched)
    }

    fun updateTopMovieIsInWatchedList(movieId: Int, isInWatched: Boolean) {
        movieDao.updateTopMovieInWatchListState(movieId = movieId, isInWatch = !isInWatched)
    }

    fun updateTopSeriesIsWatched(seriesId: Int, isWatched: Boolean) {
        seriesDao.updateTopSeriesIsWatchedState(seriesId = seriesId, isWatched = !isWatched)
    }

    fun updateTopSeriesIsInWatched(seriesId: Int, isInWatched: Boolean) {
        seriesDao.updateTopSeriesInWatchListState(seriesId = seriesId, isInWatch = !isInWatched)
    }
}