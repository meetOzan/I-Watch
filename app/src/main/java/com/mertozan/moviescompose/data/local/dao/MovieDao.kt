package com.mertozan.moviescompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity

@Dao
interface MovieDao {

    @Upsert
    fun addMovieToLocal(movieList: List<MovieEntity>)

    @Upsert
    fun addTopMoviesToLocal(topMovies: List<TopMovieEntity>)

    @Query("SELECT * FROM movies_entity")
    fun getPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM top_movies")
    fun getTopMovies(): List<TopMovieEntity>

    @Query("SELECT * FROM movies_entity WHERE movie_id = :movieId")
    fun getSingleMovie(movieId: Int): MovieEntity

    @Query("SELECT * FROM movies_entity WHERE movie_is_favorite = 1 ")
    fun getFavoritePopularMovie(): List<MovieEntity>

    @Query("SELECT * FROM movies_entity WHERE movie_is_in_watch_list = 1")
    fun getInWatchListPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies_entity WHERE movie_is_watched = 1")
    fun getWatchedPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM top_movies WHERE top_movie_is_favorite = 1")
    fun getFavoriteTopMovies(): List<TopMovieEntity>

    @Query("SELECT * FROM top_movies WHERE top_movie_is_in_watch_list = 1")
    fun getInWatchListTopMovies(): List<TopMovieEntity>

    @Query("SELECT * FROM top_movies WHERE top_movie_is_watched = 1")
    fun getWatchedTopMovies(): List<TopMovieEntity>

    @Query("SELECT * FROM top_movies WHERE top_movie_id = :movieId")
    fun getTopSingleMovie(movieId: Int): TopMovieEntity

    @Query("UPDATE movies_entity SET movie_is_favorite = :isFavorite WHERE movie_id = :movieId")
    fun updateMovieFavoriteState(movieId: Int, isFavorite: Boolean)

    @Query("UPDATE movies_entity SET movie_is_watched = :isWatched WHERE movie_id = :movieId")
    fun updateMovieIsWatchedState(movieId: Int, isWatched: Boolean)

    @Query("UPDATE movies_entity SET movie_is_in_watch_list = :isInWatch WHERE movie_id = :movieId")
    fun updateMovieInWatchListState(movieId: Int, isInWatch: Boolean)

    @Query("UPDATE top_movies SET top_movie_is_favorite = :isFavorite WHERE top_movie_id = :topMovieId")
    fun updateTopMovieFavoriteState(topMovieId: Int, isFavorite: Boolean)

    @Query("UPDATE top_movies SET top_movie_is_watched = :isWatched WHERE top_movie_id = :movieId")
    fun updateTopMovieIsWatchedState(movieId: Int, isWatched: Boolean)

    @Query("UPDATE top_movies SET top_movie_is_in_watch_list = :isInWatch WHERE top_movie_id = :movieId")
    fun updateTopMovieInWatchListState(movieId: Int, isInWatch: Boolean)

    @Delete
    fun deleteMovie(movie: MovieEntity)
}