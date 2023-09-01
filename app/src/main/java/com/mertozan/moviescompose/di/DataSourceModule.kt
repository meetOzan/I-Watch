package com.mertozan.moviescompose.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mertozan.moviescompose.data.local.dao.MovieDao
import com.mertozan.moviescompose.data.local.dao.SeriesDao
import com.mertozan.moviescompose.data.local.dao.UserDao
import com.mertozan.moviescompose.data.local.datasource.LocalDataSource
import com.mertozan.moviescompose.data.remote.firebase.FirebaseDataSource
import com.mertozan.moviescompose.data.remote.retrofit.RetrofitDataSource
import com.mertozan.moviescompose.data.remote.service.MovieService
import com.mertozan.moviescompose.data.remote.service.SeriesService
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
    fun provideFirebaseDataSource(
        firebaseDb: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): FirebaseDataSource = FirebaseDataSource(firebaseDb,firebaseAuth)

    @Provides
    @Singleton
    fun provideRetrofitModule(
        movieService: MovieService,
        seriesService: SeriesService
    ): RetrofitDataSource = RetrofitDataSource(movieService, seriesService)

    @Provides
    @Singleton
    fun provideLocalModule(
        movieDao: MovieDao,
        seriesDao: SeriesDao,
        userDao: UserDao
    ) : LocalDataSource = LocalDataSource(movieDao, seriesDao, userDao)
}