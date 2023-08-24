package com.mertozan.moviescompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mertozan.moviescompose.data.model.MovieEntity
import com.mertozan.moviescompose.data.model.SeriesEntity

@Dao
interface FavoritesDao {

    @Upsert
    fun addMovieFavorites(movieList: List<MovieEntity>)

    @Upsert
    fun addSeriesFavorites(seriesList: List<SeriesEntity>)

    @Query("SELECT * FROM movies_entity")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM series_entity")
    fun getAllSeries(): List<SeriesEntity>

    @Query("SELECT * FROM movies_entity WHERE movie_id = :movieId")
    fun getSingleLocalMovie(movieId: Int): MovieEntity

    @Query("SELECT * FROM series_entity WHERE series_id = :seriesId")
    fun getSingleLocalSeries(seriesId: Int): SeriesEntity

    @Query("UPDATE movies_entity SET movie_is_favorite = :isFavorite WHERE movie_id = :movieId")
    fun updateMovieFavoriteState(movieId: Int, isFavorite: Boolean)

    @Query("UPDATE series_entity SET series_is_favorite = :isFavorite WHERE series_id = :seriesId")
    fun updateSeriesFavoriteState(seriesId: Int, isFavorite: Boolean)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Delete
    fun deleteSeries(series: SeriesEntity)

}