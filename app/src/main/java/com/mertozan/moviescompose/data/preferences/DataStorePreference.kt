package com.mertozan.moviescompose.data.preferences

import kotlinx.coroutines.flow.Flow

interface DataStorePreference {

    suspend fun saveSelectedLanguage(selectedLanguageCode: String)

    val getSelectedLanguage: Flow<String>
}