package com.mertozan.moviescompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertozan.moviescompose.data.model.MovieEntity
import com.mertozan.moviescompose.data.model.SeriesEntity

@Database(entities = [SeriesEntity::class, MovieEntity::class], version = 4, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

}