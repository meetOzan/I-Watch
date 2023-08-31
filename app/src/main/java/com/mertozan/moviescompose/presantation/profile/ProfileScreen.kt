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
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.custom.components.CustomText
import com.mertozan.moviescompose.presantation.custom.items.ProfileOptionsCard
import com.mertozan.moviescompose.ui.theme.DarkWhite80
import com.mertozan.moviescompose.ui.theme.DarkYellow

@Composable
fun ProfileScreen(
    onNavigate: () -> Unit,
    onSignOutClick: () -> Unit,
    fullName: String,
    watched: Int
) {

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
                contentDescription = stringResource(R.string.profile_account),
                modifier = Modifier
                    .size(96.dp),
                colorFilter = ColorFilter.tint(DarkYellow)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CustomText(
                    text = fullName,
                    fontSize = 24,
                    color = DarkWhite80,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomText(
                        text = "Watched: $watched ",
                        fontSize = 20,
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
            CustomText(
                text = "Options",
                fontSize = 40,
                color = DarkWhite80,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, bottom = 24.dp)
            )

            ProfileOptionsCard(
                optionName = stringResource(R.string.favorites),
                icon = Icons.Filled.Favorite
            )
            ProfileOptionsCard(
                optionName = stringResource(R.string.settings),
                icon = Icons.Filled.Settings
            )
            ProfileOptionsCard(
                optionName = stringResource(R.string.lists),
                icon = Icons.Rounded.List
            )
            ProfileOptionsCard(
                optionName = stringResource(R.string.sign_out),
                icon = Icons.Rounded.ExitToApp,
                onClick = {
                    onSignOutClick()
                    onNavigate()
                }
            )
        }
    }
}