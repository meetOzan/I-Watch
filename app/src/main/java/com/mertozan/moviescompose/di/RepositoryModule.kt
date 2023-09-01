package com.mertozan.moviescompose.di

import com.mertozan.moviescompose.data.local.datasource.LocalDataSource
import com.mertozan.moviescompose.data.remote.firebase.FirebaseDataSource
import com.mertozan.moviescompose.data.remote.retrofit.RetrofitDataSource
import com.mertozan.moviescompose.data.repository.MovieRepositoryImpl
import com.mertozan.moviescompose.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(
        firebaseDataSource: FirebaseDataSource,
        retrofitDataSource: RetrofitDataSource,
        localDataSource: LocalDataSource
    ): MovieRepository = MovieRepositoryImpl(localDataSource, firebaseDataSource, retrofitDataSource)

}