package com.mertozan.moviescompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity

@Database(
    entities = [
        SeriesEntity::class,
        MovieEntity::class,
        TopMovieEntity::class,
        TopSeriesEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun favoritesDao(): LocalDao

}