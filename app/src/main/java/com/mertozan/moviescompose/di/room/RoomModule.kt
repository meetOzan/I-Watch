package com.mertozan.moviescompose.di.room

import android.content.Context
import androidx.room.Room
import com.mertozan.moviescompose.data.FavoritesDao
import com.mertozan.moviescompose.data.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : FavoritesDatabase {
        return Room.databaseBuilder(
            context,
            FavoritesDatabase::class.java,
            "note_database.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(favoritesDatabase: FavoritesDatabase): FavoritesDao {
        return favoritesDatabase.favoritesDao()
    }
}