package com.mertozan.moviescompose.di

import com.mertozan.moviescompose.data.local.dao.MovieDao
import com.mertozan.moviescompose.data.local.dao.SeriesDao
import com.mertozan.moviescompose.data.local.dao.UserDao
import com.mertozan.moviescompose.data.remote.service.MovieService
import com.mertozan.moviescompose.data.remote.service.SeriesService
import com.mertozan.moviescompose.data.source.local.LocalDataSource
import com.mertozan.moviescompose.data.source.remote.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRetrofitModule(
        movieService: MovieService,
        seriesService: SeriesService,
    ): RetrofitDataSource = RetrofitDataSource(movieService, seriesService)

    @Provides
    @Singleton
    fun provideLocalModule(
        movieDao: MovieDao,
        seriesDao: SeriesDao,
        userDao: UserDao,
    ): LocalDataSource = LocalDataSource(movieDao, seriesDao, userDao)
}