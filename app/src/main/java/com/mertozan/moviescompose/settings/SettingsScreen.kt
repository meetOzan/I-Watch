package com.mertozan.moviescompose.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presentation.main_components.CustomText
import com.mertozan.moviescompose.settings.component.SettingsOptionsCard

@Composable
fun SettingsScreen() {

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
                false
            )
            SettingsOptionsCard(
                optionName = stringResource(R.string.source_code),
                icon = painterResource(id = R.drawable.github_icon),
                true
            )

        }

    }
}

@Preview
@Composable
fun PrevSettings() {
    SettingsScreen()
}