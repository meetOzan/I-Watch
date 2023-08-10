package com.mertozan.moviescompose.di.network

import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.data.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Retrofit Built
    @Singleton
    @Provides
    internal fun provideRetrofitService(): MovieService = Retrofit.Builder()
        .baseUrl(BuildConfig.MOVIE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieService::class.java)

}