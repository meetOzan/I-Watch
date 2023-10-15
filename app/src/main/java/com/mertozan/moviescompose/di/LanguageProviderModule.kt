package com.mertozan.moviescompose.di

import android.content.Context
import com.mertozan.moviescompose.infrastructure.language.AppLanguageProvider
import com.mertozan.moviescompose.infrastructure.language.AppLanguageProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LanguageProviderModule {

    @Provides
    @Singleton
    fun provideLanguageProvider(
        @ApplicationContext context: Context
    ) : AppLanguageProvider = AppLanguageProviderImpl(context = context)

}