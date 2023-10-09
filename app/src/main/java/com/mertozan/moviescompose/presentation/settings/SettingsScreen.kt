package com.mertozan.moviescompose.presentation.settings

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.settings.component.SettingsOptionsCard

@Composable
fun SettingsScreen() {

    val context = LocalContext.current

    val localLanguage: String by remember {
        mutableStateOf(if (context.getString(R.string.settings) == "Settings") "en" else "tr")
    }

    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(localLanguage)
    AppCompatDelegate.setApplicationLocales(appLocale)

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CustomText(
            text = stringResource(id = R.string.settings),
            fontSize = 28,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 18.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(0.9f)
                .weight(2f)
        ) {
            SettingsOptionsCard(
                optionName = stringResource(R.string.change_theme),
                icon = painterResource(id = R.drawable.ic_light_mode),
                false
            )
            SettingsOptionsCard(
                optionName = stringResource(R.string.change_language),
                icon = painterResource(id = R.drawable.ic_language),
                false,
                onClick = {
                    localeSelection(
                        context = context,
                        localeTag = if (localLanguage == "en") "tr" else "en"
                    )
                }
            )
            SettingsOptionsCard(
                optionName = stringResource(R.string.source_code),
                icon = painterResource(id = R.drawable.github_icon),
                true
            )

        }

    }
}

fun localeSelection(context: Context, localeTag: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getSystemService(LocaleManager::class.java).applicationLocales =
            LocaleList.forLanguageTags(localeTag)
    } else {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(localeTag)
        )
    }
}

@Preview
@Composable
fun PrevSettings() {
    SettingsScreen()
}