package com.mertozan.moviescompose.di

import android.content.Context
import androidx.room.Room
import com.mertozan.moviescompose.data.local.dao.MovieDao
import com.mertozan.moviescompose.data.local.dao.SeriesDao
import com.mertozan.moviescompose.data.local.dao.UserDao
import com.mertozan.moviescompose.data.local.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideUserDao(localDatabase: LocalDatabase): UserDao {
        return localDatabase.userDao()
    }

    const val DATABASE_NAME = "note_database.db"
}
