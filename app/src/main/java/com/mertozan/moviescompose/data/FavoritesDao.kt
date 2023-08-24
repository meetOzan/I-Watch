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
    fun addMovieFavorites(movie: MovieEntity)

    @Upsert
    fun addSeriesFavorites(series: SeriesEntity)

    @Query("SELECT * FROM movies_entity")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM series_entity")
    fun getAllSeries(): List<SeriesEntity>

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Delete
    fun deleteSeries(series: SeriesEntity)

}