package com.mertozan.moviescompose.data.source.local

import com.mertozan.moviescompose.data.local.dao.MovieDao
import com.mertozan.moviescompose.data.local.dao.SeriesDao
import com.mertozan.moviescompose.data.local.dao.UserDao
import com.mertozan.moviescompose.data.mapper.toUserEntityToUserItem
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val userDao: UserDao
) {
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

    fun addUserToLocal(user: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.addUserToLocal(user)
        }
    }

    fun getSingleMovie(movieId: Int): MovieEntity {
        return movieDao.getSingleMovie(movieId = movieId)
    }

    fun getSingleSeries(seriesId: Int): SeriesEntity {
        return seriesDao.getSingleLocalSeries(seriesId = seriesId)
    }

    fun getSingleTopMovies(movieId: Int): TopMovieEntity {
        return movieDao.getTopSingleMovie(movieId)
    }

    fun getSingleTopSeries(seriesId: Int): TopSeriesEntity {
        return seriesDao.getTopSingleLocalSeries(seriesId)
    }

    fun getSingleLocalUser(): UserModel {
        return userDao.getSingleUser().toUserEntityToUserItem()
    }

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
        seriesDao.updateTopSeriesFavoriteState(topSeriesId = seriesId, isFavorite = isFavorite)
    }
}