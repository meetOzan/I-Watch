package com.mertozan.moviescompose.di

import android.content.Context
import com.mertozan.moviescompose.infrastructure.provider.StringResourceProvider
import com.mertozan.moviescompose.infrastructure.provider.StringResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideStringResourceProvider(
        @ApplicationContext context: Context
    ): StringResourceProvider = StringResourceProviderImpl(context = context)

}