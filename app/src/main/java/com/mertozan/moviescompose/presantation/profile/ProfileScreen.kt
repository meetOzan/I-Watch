package com.mertozan.moviescompose.presantation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mertozan.moviescompose.presantation.components.ProfileOptionsCard

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileOptionsCard(optionName = "Favorites", icon = Icons.Filled.Favorite)
        ProfileOptionsCard(optionName = "Settings", icon = Icons.Filled.Settings)
        ProfileOptionsCard(optionName = "Preferred Contents", icon = Icons.Rounded.List)


    }
}