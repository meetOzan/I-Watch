package com.mertozan.moviescompose.di

import com.mertozan.moviescompose.data.local.datasource.LocalDataSource
import com.mertozan.moviescompose.data.remote.firebase.FirebaseDataSource
import com.mertozan.moviescompose.data.remote.retrofit.RetrofitDataSource
import com.mertozan.moviescompose.data.repository.ContentRepositoryImpl
import com.mertozan.moviescompose.data.repository.UserRepositoryImpl
import com.mertozan.moviescompose.domain.repository.ContentRepository
import com.mertozan.moviescompose.domain.repository.UserRepository
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
    fun provideContentsRepository(
        retrofitDataSource: RetrofitDataSource,
        localDataSource: LocalDataSource
    ): ContentRepository = ContentRepositoryImpl(
        localDataSource,
        retrofitDataSource
    )

    @Provides
    @Singleton
    fun provideUserRepository(
        firebaseDataSource: FirebaseDataSource,
        localDataSource: LocalDataSource
    ): UserRepository = UserRepositoryImpl(
        localDataSource,
        firebaseDataSource
    )
}
