package com.mertozan.moviescompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity

@Database(
    entities = [SeriesEntity::class, MovieEntity::class, TopMovieEntity::class, TopSeriesEntity::class],
    version = 14,
    exportSchema = false
)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

}