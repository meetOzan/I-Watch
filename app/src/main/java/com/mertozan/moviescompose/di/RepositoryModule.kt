package com.mertozan.moviescompose.di

import com.mertozan.moviescompose.data.repository.ContentRepositoryImpl
import com.mertozan.moviescompose.data.repository.UserRepositoryImpl
import com.mertozan.moviescompose.domain.repository.ContentRepository
import com.mertozan.moviescompose.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideContentsRepository(contentRepositoryImpl: ContentRepositoryImpl): ContentRepository

    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
