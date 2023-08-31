package com.mertozan.moviescompose.di.room

import android.content.Context
import androidx.room.Room
import com.mertozan.moviescompose.common.Constants.DATABASE_NAME
import com.mertozan.moviescompose.dao.MovieDao
import com.mertozan.moviescompose.dao.SeriesDao
import com.mertozan.moviescompose.data.LocalDatabase
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
    fun provideDatabase(@ApplicationContext context: Context) : LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(localDatabase: LocalDatabase): MovieDao {
        return localDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideSeriesDao(localDatabase: LocalDatabase): SeriesDao {
        return localDatabase.seriesDao()
    }
}