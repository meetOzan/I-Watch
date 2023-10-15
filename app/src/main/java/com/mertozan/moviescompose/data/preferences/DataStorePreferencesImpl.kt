package com.mertozan.moviescompose.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(
    name = "app_language_preferences"
)

const val DATASTORE_LANGUAGE_KEY = "selected_language_code"

class DataStorePreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStorePreference {

    companion object {
        private val SELECTED_LANGUAGE_CODE = stringPreferencesKey(DATASTORE_LANGUAGE_KEY)
    }

    override suspend fun saveSelectedLanguage(selectedLanguageCode: String) {
        context.datastore.edit { preferences ->
            preferences[SELECTED_LANGUAGE_CODE] = selectedLanguageCode
        }
    }

    override val getSelectedLanguage: Flow<String> = context.datastore.data.map { preferences ->
        preferences[SELECTED_LANGUAGE_CODE] ?: "en"
    }
}