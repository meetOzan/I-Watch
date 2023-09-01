package com.mertozan.moviescompose.domain.repository

import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.model.dto.MovieResponse
import com.mertozan.moviescompose.data.model.dto.SeriesResponse
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.model.UserModel

interface MovieRepository {

    fun getAllPopularMovies(): List<ContentModel>

    fun getAllPopularSeries(): List<ContentModel>

    fun getAllTopRatedMovies(): List<ContentModel>

    fun getAllTopRatedSeries(): List<ContentModel>

    fun addPopularMoviesToLocal(movieItem: List<ContentModel>)

    fun addPopularSeriesToLocal(seriesItem: List<ContentModel>)

    fun addTopRatedMoviesToLocal(movieItem: List<ContentModel>)

    fun addTopRatedSeries(seriesItem: List<ContentModel>)

    fun addUserToLocal(user: UserEntity)

    fun getSingleMovie(movieId: Int): MovieEntity

    fun getSingleSeries(seriesId: Int): SeriesEntity

    fun getSingleTopMovies(movieId: Int): TopMovieEntity

    fun getSingleTopSeries(seriesId: Int): TopSeriesEntity

    fun getSingleLocalUser(): UserModel

    fun updateMovieFavorite(movieId: Int, isFavorite: Boolean)

    fun updateSeriesFavorite(seriesId: Int, isFavorite: Boolean)

    fun updateTopMovieFavorite(movieId: Int, isFavorite: Boolean)

    fun updateTopSeriesFavorite(seriesId: Int, isFavorite: Boolean)

    suspend fun getUserFromLocale(): UserModel

    suspend fun getUserFromNetwork() : UserModel

    suspend fun signOut()

    suspend fun getAllPopularNetworkMovies(): MovieResponse

    suspend fun getAllPopularNetworkSeries(): SeriesResponse

    suspend fun getAllTopRatedNetworkMovies(): MovieResponse

    suspend fun getAllNetworkTopRatedSeries(): SeriesResponse

    suspend fun getMovieGenres(): GenresResponse

    suspend fun getSeriesGenres(): GenresResponse

    suspend fun transferUserToLocal(userModel: UserModel)

}