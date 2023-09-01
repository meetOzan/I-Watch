package com.mertozan.moviescompose.data.local.datasource

import com.mertozan.moviescompose.data.local.dao.MovieDao
import com.mertozan.moviescompose.data.local.dao.SeriesDao
import com.mertozan.moviescompose.data.local.dao.UserDao
import com.mertozan.moviescompose.data.mapper.movieModelToMovieEntityList
import com.mertozan.moviescompose.data.mapper.movieModelToTopMovieEntityList
import com.mertozan.moviescompose.data.mapper.moviesToMoviesModelList
import com.mertozan.moviescompose.data.mapper.toDetailItemToSeriesEntityList
import com.mertozan.moviescompose.data.mapper.toDetailItemToTopSeriesEntityList
import com.mertozan.moviescompose.data.mapper.toSeriesDetailItemList
import com.mertozan.moviescompose.data.mapper.toTopSeriesDetailItemList
import com.mertozan.moviescompose.data.mapper.toUserEntityToUserItem
import com.mertozan.moviescompose.data.mapper.topMoviesToMovieModelList
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.ContentModel
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
    fun getAllPopularMovies(): List<ContentModel> {
        return movieDao.getPopularMovies().moviesToMoviesModelList()
    }

    fun getAllPopularSeries(): List<ContentModel> {
        return seriesDao.getPopularSeries().toSeriesDetailItemList()
    }

    fun getAllTopRatedMovies(): List<ContentModel> {
        return movieDao.getTopMovies().topMoviesToMovieModelList()
    }

    fun getAllTopRatedSeries(): List<ContentModel> {
        return seriesDao.getTopSeries().toTopSeriesDetailItemList()
    }

    fun addPopularMoviesToLocal(movieItem: List<ContentModel>) {
        movieDao.addMovieToLocal(movieItem.movieModelToMovieEntityList())
    }

    fun addPopularSeriesToLocal(seriesItem: List<ContentModel>) {
        seriesDao.addSeriesToLocal(seriesItem.toDetailItemToSeriesEntityList())
    }

    fun addTopRatedMoviesToLocal(movieItem: List<ContentModel>) {
        movieDao.addTopMoviesToLocal(movieItem.movieModelToTopMovieEntityList())
    }

    fun addTopRatedSeries(seriesItem: List<ContentModel>) {
        seriesDao.addTopSeriesToLocal(seriesItem.toDetailItemToTopSeriesEntityList())
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
        seriesDao.updateSeriesFavoriteState(seriesId = seriesId, isFavorite = isFavorite)
    }

    fun updateTopMovieFavorite(movieId: Int, isFavorite: Boolean) {
        movieDao.updateTopMovieFavoriteState(topMovieId = movieId, isFavorite = !isFavorite)
    }

    fun updateTopSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        seriesDao.updateTopSeriesFavoriteState(topSeriesId = seriesId, isFavorite = isFavorite)
    }
}