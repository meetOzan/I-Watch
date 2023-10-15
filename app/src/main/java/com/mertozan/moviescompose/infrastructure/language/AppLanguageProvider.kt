package com.mertozan.moviescompose.infrastructure.language

import android.content.Context

interface AppLanguageProvider{

    val context: Context

    fun getAppLanguage(): String

    fun setAppLanguage(languageCode: String?)

    fun setLocale(language: String)
    
}