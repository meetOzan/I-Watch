package com.mertozan.moviescompose.presantation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.moviescompose.presantation.components.ProfileOptionsCard
import com.mertozan.moviescompose.ui.theme.DarkWhite80
import com.mertozan.moviescompose.ui.theme.DarkYellow
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 56.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "profile_account",
                modifier = Modifier
                    .size(96.dp),
                colorFilter = ColorFilter.tint(DarkYellow)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Mert Ozan Kahraman",
                    fontSize = 24.sp,
                    fontFamily = amazonEmberFamily,
                    color = DarkWhite80,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Watched: ${20} ",
                        fontSize = 20.sp,
                        fontFamily = amazonEmberFamily,
                        color = DarkWhite80,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Options",
                fontSize = 40.sp,
                fontFamily = amazonEmberFamily,
                color = DarkWhite80,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, bottom = 24.dp)
            )

            ProfileOptionsCard(optionName = "Favorites", icon = Icons.Filled.Favorite)
            ProfileOptionsCard(optionName = "Settings", icon = Icons.Filled.Settings)
            ProfileOptionsCard(optionName = "Preferred Contents", icon = Icons.Rounded.Refresh)
            ProfileOptionsCard(optionName = "Lists", icon = Icons.Rounded.List)
        }

    }
}