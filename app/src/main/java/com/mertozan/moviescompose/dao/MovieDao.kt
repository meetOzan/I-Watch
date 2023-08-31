package com.mertozan.moviescompose.dao

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

    @Query("SELECT * FROM top_movies WHERE top_movie_id = :movieId")
    fun getTopSingleMovie(movieId: Int): TopMovieEntity

    @Query("UPDATE movies_entity SET movie_is_favorite = :isFavorite WHERE movie_id = :movieId")
    fun updateMovieFavoriteState(movieId: Int, isFavorite: Boolean)

    @Query("UPDATE top_movies SET top_movie_is_favorite = :isFavorite WHERE top_movie_id = :topMovieId")
    fun updateTopMovieFavoriteState(topMovieId: Int, isFavorite: Boolean)

    @Delete
    fun deleteMovie(movie: MovieEntity)
}