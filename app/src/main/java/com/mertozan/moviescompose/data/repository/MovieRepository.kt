package com.mertozan.moviescompose.data.repository

import android.content.res.Resources.NotFoundException
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mertozan.moviescompose.data.LocalDao
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
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val roomDao: LocalDao,
) {

    init {
        transferToLocal()
    }

    // Get Network
    private suspend fun getAllPopularMovies(): MovieResponse {
        return movieService.getPopularMovies()
    }

    private suspend fun getAllPopularSeries(): SeriesResponse {
        return movieService.getPopularSeries()
    }

    private suspend fun getAllTopRatedMovies(): MovieResponse {
        return movieService.getTopRatedMovies()
    }

    private suspend fun getAllTopRatedSeries(): SeriesResponse {
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
    fun getAllPopularLocalMovies(): List<DetailItem> {
        return roomDao.getPopularMovies().toMoviesToDetailItemList()
    }

    fun getAllPopularLocalSeries(): List<DetailItem> {
        return roomDao.getPopularSeries().toSeriesDetailItemList()
    }

    fun getAllTopRatedLocalMovies(): List<DetailItem> {
        return roomDao.getTopMovies().toTopMoviesToDetailItemList()
    }

    fun getAllTopRatedLocalSeries(): List<DetailItem> {
        return roomDao.getTopSeries().toTopSeriesDetailItemList()
    }

    // Add to Local
    private fun addPopularMoviesToLocal(movieItem: List<DetailItem>) {
        roomDao.addMovieToLocal(movieItem.toDetailItemToMovieEntityList())
    }

    private fun addPopularSeriesToLocal(seriesItem: List<DetailItem>) {
        roomDao.addSeriesToLocal(seriesItem.toDetailItemToSeriesEntityList())
    }

    private fun addTopRatedMoviesToLocal(movieItem: List<DetailItem>) {
        roomDao.addTopMoviesToLocal(movieItem.toDetailItemToTopMovieEntityList())
    }

    private fun addTopRatedSeriesToLocal(seriesItem: List<DetailItem>) {
        roomDao.addTopSeriesToLocal(seriesItem.toDetailItemToTopSeriesEntityList())
    }

    private fun addUserToLocal(user: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            roomDao.addUserToLocal(user)
        }
    }

    // Get single
    fun getSingleLocalMovie(movieId: Int): MovieEntity {
        return roomDao.getSingleLocalMovie(movieId = movieId)
    }

    fun getSingleLocalSeries(seriesId: Int): SeriesEntity {
        return roomDao.getSingleLocalSeries(seriesId = seriesId)
    }

    fun getSingleTopLocalMovies(movieId: Int): TopMovieEntity {
        return roomDao.getTopSingleLocalMovie(movieId)
    }

    fun getSingleTopLocalSeries(seriesId: Int): TopSeriesEntity {
        return roomDao.getTopSingleLocalSeries(seriesId)
    }

    fun getSingleLocalUser(): UserItem {
        return roomDao.getSingleUser().toUserEntityToUserItem()
    }

    // Update
    fun updateMovieFavorite(movieId: Int, isFavorite: Boolean) {
        roomDao.updateMovieFavoriteState(movieId = movieId, isFavorite = !isFavorite)
    }

    fun updateSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        roomDao.updateSeriesFavoriteState(seriesId = seriesId, isFavorite = isFavorite)
    }

    fun updateTopMovieFavorite(movieId: Int, isFavorite: Boolean) {
        roomDao.updateTopMovieFavoriteState(topMovieId = movieId, isFavorite = !isFavorite)
    }

    fun updateTopSeriesFavorite(seriesId: Int, isFavorite: Boolean) {
        roomDao.updateTopSeriesFavoriteState(topSeriesId = seriesId, isFavorite = isFavorite)
    }

    // Firestore Operations
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
    }

    suspend fun transferUserToLocal() {
        val user = getUserFromNetwork()
        addUserToLocal(
            user.toUserItemToUserEntity()
        )
    }

    // Transfer
    private fun transferToLocal() {
        CoroutineScope(Dispatchers.IO).launch {

            if (getAllPopularLocalMovies().isEmpty()) {
                addPopularMoviesToLocal(
                    getAllPopularMovies()
                        .movieResults.moviesToDetailItemList()
                )
            }
            if (getAllPopularLocalSeries().isEmpty()) {
                addPopularSeriesToLocal(
                    getAllPopularSeries()
                        .seriesResults.seriesToDetailItemList()
                )
            }
            if (getAllTopRatedLocalMovies().isEmpty()) {
                addTopRatedMoviesToLocal(
                    getAllTopRatedMovies()
                        .movieResults.moviesToDetailItemList()
                )
            }
            if (getAllTopRatedLocalSeries().isEmpty()) {
                addTopRatedSeriesToLocal(
                    getAllTopRatedSeries()
                        .seriesResults.seriesToDetailItemList()
                )
            }
        }
    }
}