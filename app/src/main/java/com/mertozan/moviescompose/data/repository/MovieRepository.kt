package com.mertozan.moviescompose.data.repository

import android.content.res.Resources.NotFoundException
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mertozan.moviescompose.common.Constants.COLLECTION_NAME
import com.mertozan.moviescompose.dao.MovieDao
import com.mertozan.moviescompose.dao.SeriesDao
import com.mertozan.moviescompose.data.api.MovieService
import com.mertozan.moviescompose.data.mapper.moviesToDetailItemList
import com.mertozan.moviescompose.data.mapper.seriesToDetailItemList
import com.mertozan.moviescompose.data.mapper.toDetailItemToMovieEntityList
import com.mertozan.moviescompose.data.mapper.toDetailItemToSeriesEntityList
import com.mertozan.moviescompose.data.mapper.toDetailItemToTopMovieEntityList
import com.mertozan.moviescompose.data.mapper.toDetailItemToTopSeriesEntityList
import com.mertozan.moviescompose.data.mapper.toMoviesToDetailItemList
import com.mertozan.moviescompose.data.mapper.toSeriesDetailItemList
import com.mertozan.moviescompose.data.mapper.toTopMoviesToDetailItemList
import com.mertozan.moviescompose.data.mapper.toTopSeriesDetailItemList
import com.mertozan.moviescompose.data.mapper.toUserEntityToUserItem
import com.mertozan.moviescompose.data.mapper.toUserItemToUserEntity
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.data.model.response.GenresResponse
import com.mertozan.moviescompose.data.model.response.MovieResponse
import com.mertozan.moviescompose.data.model.response.SeriesResponse
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.domain.model.UserItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao
) {

    init {
        transferToLocal()
    }

    // Get Network
    private suspend fun getAllPopularNetworkMovies(): MovieResponse {
        return movieService.getPopularMovies()
    }

    private suspend fun getAllPopularNetworkSeries(): SeriesResponse {
        return movieService.getPopularSeries()
    }

    private suspend fun getAllTopRatedNetworkMovies(): MovieResponse {
        return movieService.getTopRatedMovies()
    }

    private suspend fun getAllNetworkTopRatedSeries(): SeriesResponse {
        return movieService.getTopRatedSeries()
    }

    // Get Genres
    suspend fun getMovieGenres(): GenresResponse {
        return movieService.getMovieGenres()
    }

    suspend fun getSeriesGenres(): GenresResponse {
        return movieService.getSeriesGenres()
    }

    // Sign Out
    fun signOut() {
        CoroutineScope(Dispatchers.IO).launch {
            auth.signOut()
        }
    }

    // Get Local
    fun getAllPopularMovies(): List<DetailItem> {
        return movieDao.getPopularMovies().toMoviesToDetailItemList()
    }

    fun getAllPopularSeries(): List<DetailItem> {
        return seriesDao.getPopularSeries().toSeriesDetailItemList()
    }

    fun getAllTopRatedMovies(): List<DetailItem> {
        return movieDao.getTopMovies().toTopMoviesToDetailItemList()
    }

    fun getAllTopRatedSeries(): List<DetailItem> {
        return seriesDao.getTopSeries().toTopSeriesDetailItemList()
    }

    // Add to Local
    private fun addPopularMoviesToLocal(movieItem: List<DetailItem>) {
        movieDao.addMovieToLocal(movieItem.toDetailItemToMovieEntityList())
    }

    private fun addPopularSeriesToLocal(seriesItem: List<DetailItem>) {
        seriesDao.addSeriesToLocal(seriesItem.toDetailItemToSeriesEntityList())
    }

    private fun addTopRatedMoviesToLocal(movieItem: List<DetailItem>) {
        movieDao.addTopMoviesToLocal(movieItem.toDetailItemToTopMovieEntityList())
    }

    private fun addTopRatedSeries(seriesItem: List<DetailItem>) {
        seriesDao.addTopSeriesToLocal(seriesItem.toDetailItemToTopSeriesEntityList())
    }

    private fun addUserToLocal(user: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            roomDao.addUserToLocal(user)
        }
    }

    // Get single
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

    fun getSingleLocalUser(): UserItem {
        return roomDao.getSingleUser().toUserEntityToUserItem()
    }

    // Update
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

    /*// Firestore Operations
    private suspend fun getUserFromNetwork() = suspendCoroutine { continuation ->
        db.collection("users").document(auth.currentUser?.uid.toString())
            .get().addOnSuccessListener {
                if (it.exists()) {
                    val user = it.toObject<UserItem>()!!
                    continuation.resumeWith(Result.success(user))
                } else {
                    Log.e("Get User Error: ", "User doesn't exist")
                    continuation.resumeWithException(NotFoundException())
                }
            }.addOnFailureListener {
                Log.e("Get User Exception: ", it.message.orEmpty())
                continuation.resumeWithException(it)
            }
    }*/

    suspend fun transferUserToLocal() {
        val user = getUserFromNetwork()
        addUserToLocal(
            user.toUserItemToUserEntity()
        )
    }

    // Transfer
    private fun transferToLocal() {
        CoroutineScope(Dispatchers.IO).launch {
            addPopularMoviesToLocal(
                getAllPopularNetworkMovies()
                    .movieResults.moviesToDetailItemList()
            )
            addPopularSeriesToLocal(
                getAllPopularNetworkSeries()
                    .seriesResults.seriesToDetailItemList()
            )
            addTopRatedMoviesToLocal(
                getAllTopRatedNetworkMovies()
                    .movieResults.moviesToDetailItemList()
            )
            addTopRatedSeries(
                getAllNetworkTopRatedSeries()
                    .seriesResults.seriesToDetailItemList()
            )

            if (getAllPopularMovies().isEmpty()) {
                addPopularMoviesToLocal(
                    getAllPopularNetworkMovies()
                        .movieResults.moviesToDetailItemList()
                )
            }
            if (getAllPopularSeries().isNotEmpty()) {
                addPopularSeriesToLocal(
                    getAllPopularNetworkSeries()
                        .seriesResults.seriesToDetailItemList()
                )
            }
            if (getAllTopRatedMovies().isEmpty()) {
                addTopRatedMoviesToLocal(
                    getAllTopRatedNetworkMovies()
                        .movieResults.moviesToDetailItemList()
                )
            }
            if (getAllTopRatedSeries().isNotEmpty()) {
                addTopRatedSeries(
                    getAllNetworkTopRatedSeries()
                        .seriesResults.seriesToDetailItemList()
                )
            }
        }
    }

    // Firestore Operations
    suspend fun getUser(): UserItem {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                db.collection(COLLECTION_NAME).document(auth.currentUser?.uid.toString())
                    .get().await().toObject<UserItem>() ?: UserItem()
            } catch (e: Exception) {
                UserItem()
            }
        }.await()
    }
}

