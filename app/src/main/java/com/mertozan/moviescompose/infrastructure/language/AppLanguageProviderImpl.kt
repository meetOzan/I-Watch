package com.mertozan.moviescompose.infrastructure.language

import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import javax.inject.Inject

class AppLanguageProviderImpl @Inject constructor(
    override val context: Context,
) : AppLanguageProvider {

    override fun getAppLanguage(): String {
        val configuration = context.resources.configuration
        return when (Locale.forLanguageTag(configuration.locale.language)) {
            Locale("tr") -> "tr"
            Locale("en") -> "en"
            else -> "en"
        }
    }

    override fun setAppLanguage(languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = context.resources
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config: Configuration = Configuration()
        config.setLocale(locale)
    }
}