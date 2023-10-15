package com.mertozan.moviescompose.di

import android.content.Context
import com.mertozan.moviescompose.data.preferences.DataStorePreference
import com.mertozan.moviescompose.data.preferences.DataStorePreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStorePreference = DataStorePreferencesImpl(context = context)

}