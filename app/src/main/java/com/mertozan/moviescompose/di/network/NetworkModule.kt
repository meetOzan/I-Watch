package com.mertozan.moviescompose.di.network

import com.mertozan.moviescompose.common.constants.Constants.BASE_URL
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
    fun provideRetrofitService(): MovieService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieService::class.java)

}