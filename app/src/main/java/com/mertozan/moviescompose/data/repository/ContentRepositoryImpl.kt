package com.mertozan.moviescompose.data.repository

import com.mertozan.moviescompose.data.mapper.movieModelToMovieEntityList
import com.mertozan.moviescompose.data.mapper.movieModelToTopMovieEntityList
import com.mertozan.moviescompose.data.mapper.moviesToMovieModelList
import com.mertozan.moviescompose.data.mapper.seriesToSeriesModelList
import com.mertozan.moviescompose.data.mapper.toDetailItemToSeriesEntityList
import com.mertozan.moviescompose.data.mapper.toDetailItemToTopSeriesEntityList
import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.model.dto.MovieResponse
import com.mertozan.moviescompose.data.model.dto.SeriesResponse
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.source.local.LocalDataSource
import com.mertozan.moviescompose.data.source.remote.FirebaseDataSource
import com.mertozan.moviescompose.data.source.remote.RetrofitDataSource
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val retrofitDataSource: RetrofitDataSource,
    private val firebaseDataSource: FirebaseDataSource
) : ContentRepository {

    init {
        transferRemoteToLocal()
    }

    override fun getAllPopularMovies(): List<MovieEntity> {
        return localDataSource.getAllPopularMovies()
    }

    override fun getAllPopularSeries(): List<SeriesEntity> {
        return localDataSource.getAllPopularSeries()
    }

    override fun getAllTopRatedMovies(): List<TopMovieEntity> {
        return localDataSource.getAllTopRatedMovies()
    }

    override fun getAllTopRatedSeries(): List<TopSeriesEntity> {
        return localDataSource.getAllTopRatedSeries()
    }

    override fun addPopularMoviesToLocal(movieItem: List<MovieEntity>) {
        localDataSource.addPopularMoviesToLocal(movieItem = movieItem)
    }

    override fun addPopularSeriesToLocal(seriesItem: List<SeriesEntity>) {
        localDataSource.addPopularSeriesToLocal(seriesItem = seriesItem)
    }

    override fun addTopRatedMoviesToLocal(movieItem: List<TopMovieEntity>) {
        localDataSource.addTopRatedMoviesToLocal(movieItem)
    }

    override fun addTopRatedSeries(seriesItem: List<TopSeriesEntity>) {
        localDataSource.addTopRatedSeries(seriesItem = seriesItem)
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

    override fun getFavoriteTopMovies(): List<TopMovieEntity> {
        return localDataSource.getFavoriteTopMovie()
    }

    override fun getFavoritePopularMovies(): List<MovieEntity> {
        return localDataSource.getFavoritePopularMovie()
    }

    override fun getFavoriteTopSeries(): List<TopSeriesEntity> {
        return localDataSource.getFavoriteTopSeries()
    }

    override fun getFavoritePopularSeries(): List<SeriesEntity> {
        return localDataSource.getFavoritePopularSeries()
    }

    override fun getAllWatchedPopularMovies(): List<MovieEntity> {
        return localDataSource.getWatchedPopularMovies()
    }

    override fun getAllWatchedTopMovies(): List<TopMovieEntity> {
        return localDataSource.getWatchedTopMovies()
    }

    override fun getAllWatchedPopularSeries(): List<SeriesEntity> {
        return localDataSource.getWatchedPopularSeries()
    }

    override fun getAllWatchedTopSeries(): List<TopSeriesEntity> {
        return localDataSource.getWatchedTopSeries()
    }

    override fun getAllInWatchListPopularMovies(): List<MovieEntity> {
        return localDataSource.getInWatchListPopularMovies()
    }

    override fun getAllInWatchListTopMovies(): List<TopMovieEntity> {
        return localDataSource.getInWatchListTopMovies()
    }

    override fun getAllInWatchListPopularSeries(): List<SeriesEntity> {
        return localDataSource.getInWatchListPopularSeries()
    }

    override fun getAllInWatchListTopSeries(): List<TopSeriesEntity> {
        return localDataSource.getInWatchListTopSeries()
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

    // Update Watch State
    override fun updatePopularMoviesIsWatched(movieId: Int, isWatched: Boolean) {
        localDataSource.updateMovieIsWatched(movieId = movieId, isWatched = isWatched)
    }

    override fun updatePopularMoviesIsInWatchList(movieId: Int, isInWatched: Boolean) {
        localDataSource.updateMovieIsInWatchedList(movieId = movieId, isInWatched = isInWatched)
    }

    override fun updatePopularSeriesIsWatched(seriesId: Int, isWatched: Boolean) {
        localDataSource.updateSeriesIsWatched(seriesId = seriesId, isWatched = isWatched)
    }

    override fun updatePopularSeriesIsInWatchedList(seriesId: Int, isInWatched: Boolean) {
        localDataSource.updateSeriesIsInWatchedList(seriesId = seriesId, isInWatched = isInWatched)
    }

    override fun updateTopMoviesIsWatched(movieId: Int, isWatched: Boolean) {
        localDataSource.updateTopMovieIsWatched(movieId = movieId, isWatched = isWatched)
    }

    override fun updateTopMoviesIsInWatchList(movieId: Int, isInWatched: Boolean) {
        localDataSource.updateTopMovieIsInWatchedList(movieId = movieId, isInWatched = isInWatched)
    }

    override fun updateTopSeriesIsWatched(seriesId: Int, isWatched: Boolean) {
        localDataSource.updateTopSeriesIsWatched(seriesId = seriesId, isWatched = isWatched)
    }

    override fun updateTopSeriesIsInWatchedList(seriesId: Int, isInWatched: Boolean) {
        localDataSource.updateTopSeriesIsInWatched(seriesId = seriesId, isInWatched = isInWatched)
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

    override suspend fun getAllFavorites(favoriteList: MutableList<ContentModel>) {
        firebaseDataSource.getFavorites(favoriteList = favoriteList)
    }

    override suspend fun addContentTopMovieFavorite(id: Int, hashMap: HashMap<Any, Any>) {
        firebaseDataSource.addContentTopMovieFavorite(id = id, hashMap = hashMap)
    }

    override suspend fun deleteContentTopMovieFavorite(id: Int) {
        firebaseDataSource.deleteContentTopMovieFavorite(id = id)
    }

    override suspend fun addContentTopSeriesFavorite(id: Int, hashMap: HashMap<Any, Any>) {
        firebaseDataSource.addContentTopSeriesFavorite(id = id, hashMap = hashMap)
    }

    override suspend fun deleteContentTopSeriesFavorite(id: Int) {
        firebaseDataSource.deleteContentTopSeriesFavorite(id = id)
    }

    override suspend fun addContentPopularMoviesFavorite(id: Int, hashMap: HashMap<Any, Any>) {
        firebaseDataSource.addContentPopularMoviesFavorite(id = id, hashMap = hashMap)
    }

    override suspend fun deleteContentPopularMoviesFavorite(id: Int) {
        firebaseDataSource.deleteContentPopularMoviesFavorite(id = id)
    }

    override suspend fun addContentPopularSeriesFavorite(id: Int, hashMap: HashMap<Any, Any>) {
        firebaseDataSource.addContentPopularSeriesFavorite(id = id, hashMap = hashMap)
    }

    override suspend fun deleteContentPopularSeriesFavorite(id: Int) {
        firebaseDataSource.deleteContentPopularSeriesFavorite(id = id)
    }

    override fun transferRemoteToLocal() {
        CoroutineScope(Dispatchers.IO).launch {

            /**
             * DTO -> Model -> Entity
             */

            if (getAllPopularMovies().isEmpty()) {
                addPopularMoviesToLocal(
                    getAllPopularNetworkMovies()
                        .movieResults
                        .moviesToMovieModelList()
                        .movieModelToMovieEntityList()
                )
            }
            if (getAllPopularSeries().isEmpty()) {
                addPopularSeriesToLocal(
                    getAllPopularNetworkSeries()
                        .seriesResults
                        .seriesToSeriesModelList()
                        .toDetailItemToSeriesEntityList()
                )
            }
            if (getAllTopRatedMovies().isEmpty()) {
                addTopRatedMoviesToLocal(
                    getAllTopRatedNetworkMovies()
                        .movieResults.moviesToMovieModelList()
                        .movieModelToTopMovieEntityList()
                )
            }
            if (getAllTopRatedSeries().isEmpty()) {
                addTopRatedSeries(
                    getAllNetworkTopRatedSeries()
                        .seriesResults.seriesToSeriesModelList()
                        .toDetailItemToTopSeriesEntityList()
                )
            }
        }
    }

    override suspend fun manualTransferRemoteToLocal() {
        CoroutineScope(Dispatchers.IO).launch {

            addPopularMoviesToLocal(
                getAllPopularNetworkMovies()
                    .movieResults
                    .moviesToMovieModelList()
                    .movieModelToMovieEntityList()
            )

            addPopularSeriesToLocal(
                getAllPopularNetworkSeries()
                    .seriesResults
                    .seriesToSeriesModelList()
                    .toDetailItemToSeriesEntityList()
            )

            addTopRatedMoviesToLocal(
                getAllTopRatedNetworkMovies()
                    .movieResults.moviesToMovieModelList()
                    .movieModelToTopMovieEntityList()
            )

            addTopRatedSeries(
                getAllNetworkTopRatedSeries()
                    .seriesResults.seriesToSeriesModelList()
                    .toDetailItemToTopSeriesEntityList()
            )
        }
    }
}

object CollectionName {
    const val COLLECTION_NAME = "users"
}