package com.mertozan.moviescompose.data.repository

import com.mertozan.moviescompose.data.local.datasource.LocalDataSource
import com.mertozan.moviescompose.data.mapper.moviesToMovieModelList
import com.mertozan.moviescompose.data.mapper.seriestoSeriesModelList
import com.mertozan.moviescompose.data.mapper.toUserItemToUserEntity
import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.model.dto.MovieResponse
import com.mertozan.moviescompose.data.model.dto.SeriesResponse
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.data.remote.firebase.FirebaseDataSource
import com.mertozan.moviescompose.data.remote.retrofit.RetrofitDataSource
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

object CollectionName {
    const val COLLECTION_NAME = "users"
}

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val firebaseDataSource: FirebaseDataSource,
    private val retrofitDataSource: RetrofitDataSource
) : MovieRepository {

    init {
        transferToLocal()
    }

    override fun getAllPopularMovies(): List<ContentModel> {
        return localDataSource.getAllPopularMovies()
    }

    override fun getAllPopularSeries(): List<ContentModel> {
        return localDataSource.getAllPopularSeries()
    }

    override fun getAllTopRatedMovies(): List<ContentModel> {
        return localDataSource.getAllTopRatedMovies()
    }

    override fun getAllTopRatedSeries(): List<ContentModel> {
        return localDataSource.getAllTopRatedSeries()
    }

    override fun addPopularMoviesToLocal(movieItem: List<ContentModel>) {
        localDataSource.addPopularMoviesToLocal(movieItem = movieItem)
    }

    override fun addPopularSeriesToLocal(seriesItem: List<ContentModel>) {
        localDataSource.addPopularSeriesToLocal(seriesItem = seriesItem)
    }

    override fun addTopRatedMoviesToLocal(movieItem: List<ContentModel>) {
        localDataSource.addTopRatedMoviesToLocal(movieItem)
    }

    override fun addTopRatedSeries(seriesItem: List<ContentModel>) {
        localDataSource.addTopRatedSeries(seriesItem = seriesItem)
    }

    override fun addUserToLocal(user: UserEntity) {
        localDataSource.addUserToLocal(user = user)
    }

    override fun getSingleMovie(movieId: Int): MovieEntity {
        return localDataSource.getSingleMovie(movieId = movieId)
    }

    override fun getSingleSeries(seriesId: Int): SeriesEntity {
        return localDataSource.getSingleSeries(seriesId)
    }

    override fun getSingleTopMovies(movieId: Int): TopMovieEntity {
        return localDataSource.getSingleTopMovies(movieId = movieId)
    }

    override fun getSingleTopSeries(seriesId: Int): TopSeriesEntity {
        return localDataSource.getSingleTopSeries(seriesId = seriesId)
    }

    override fun getSingleLocalUser(): UserModel {
        return localDataSource.getSingleLocalUser()
    }

    override fun updateMovieFavorite(movieId: Int, isFavorite: Boolean) {
        localDataSource.updateMovieFavorite(movieId = movieId, isFavorite = isFavorite)
    }

    override fun updateSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        localDataSource.updateSeriesFavorite(seriesId = seriesId, isFavorite = isFavorite)
    }

    override fun updateTopMovieFavorite(movieId: Int, isFavorite: Boolean) {
        localDataSource.updateTopMovieFavorite(movieId = movieId, isFavorite = isFavorite)
    }

    override fun updateTopSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        localDataSource.updateTopSeriesFavorite(seriesId = seriesId, isFavorite = isFavorite)
    }

    override suspend fun getUserFromLocale(): UserModel {
        return firebaseDataSource.getUser()
    }

    override suspend fun getUserFromNetwork(): UserModel {
        return firebaseDataSource.getUserFromNetwork()
    }

    override suspend fun signOut() {
        firebaseDataSource.signOut()
    }

    // Network
    override suspend fun getAllPopularNetworkMovies(): MovieResponse {
        return retrofitDataSource.getAllPopularNetworkMovies()
    }

    override suspend fun getAllPopularNetworkSeries(): SeriesResponse {
        return retrofitDataSource.getAllPopularNetworkSeries()
    }

    override suspend fun getAllTopRatedNetworkMovies(): MovieResponse {
        return retrofitDataSource.getAllTopRatedNetworkMovies()
    }

    override suspend fun getAllNetworkTopRatedSeries(): SeriesResponse {
        return retrofitDataSource.getAllNetworkTopRatedSeries()
    }

    override suspend fun getMovieGenres(): GenresResponse {
        return retrofitDataSource.getMovieGenres()
    }

    override suspend fun getSeriesGenres(): GenresResponse {
        return retrofitDataSource.getSeriesGenres()
    }

    override suspend fun transferUserToLocal(userModel: UserModel) {
        localDataSource.addUserToLocal(userModel.toUserItemToUserEntity())
    }

    // Transfer
    private fun transferToLocal() {
        CoroutineScope(Dispatchers.IO).launch {

            if (getAllPopularMovies().isEmpty()) {
                addPopularMoviesToLocal(
                    getAllPopularNetworkMovies()
                        .movieResults.moviesToMovieModelList()
                )
            }
            if (getAllPopularSeries().isEmpty()) {
                addPopularSeriesToLocal(
                    getAllPopularNetworkSeries()
                        .seriesResults.seriestoSeriesModelList()
                )
            }
            if (getAllTopRatedMovies().isEmpty()) {
                addTopRatedMoviesToLocal(
                    getAllTopRatedNetworkMovies()
                        .movieResults.moviesToMovieModelList()
                )
            }
            if (getAllTopRatedSeries().isEmpty()) {
                addTopRatedSeries(
                    getAllNetworkTopRatedSeries()
                        .seriesResults.seriestoSeriesModelList()
                )
            }
        }
    }
}