package com.mertozan.moviescompose.presentation.profile

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
import androidx.navigation.NavController
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.navigation.ContentListScreen
import com.mertozan.moviescompose.presentation.profile.components.ProfileOptionsCard
import com.mertozan.moviescompose.presentation.profile.viewmodel.ProfileAction
import com.mertozan.moviescompose.presentation.profile.viewmodel.ProfileUiState
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.util.enums.ContentListType

@Composable
fun ProfileScreen(
    navController: NavController,
    fullName: String,
    watched: Int,
    profileUiState: ProfileUiState,
    onSignOutNavigate: () -> Unit,
    onProfileAction: (ProfileAction) -> Unit,
    onWatchListClick: () -> Unit,
    onSettingsClick: () -> Unit
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
            if (profileUiState.isLoading) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomText(
                        text = stringResource(R.string.still_waiting),
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
                            text = stringResource(R.string.watched_calculated),
                            fontSize = 20,
                            color = DarkWhite80,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            } else {
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
                            text = stringResource(R.string.watched, watched),
                            fontSize = 20,
                            color = DarkWhite80,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = stringResource(R.string.options),
                fontSize = 40,
                color = DarkWhite80,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, bottom = 24.dp)
            )
            ProfileOptionsCard(
                optionName = stringResource(R.string.favorites),
                icon = Icons.Filled.Favorite,
                contentListType = ContentListType.FAVORITE_CONTENTS.name,
                onToListClick = {
                    navController.navigate(
                        ContentListScreen.navigateWithArgs(
                            type = ContentListType.FAVORITE_CONTENTS.name
                        )
                    )
                }
            )
            ProfileOptionsCard(
                optionName = stringResource(R.string.settings),
                icon = Icons.Filled.Settings,
                onClick = { onSettingsClick() }
            )
            ProfileOptionsCard(
                optionName = stringResource(R.string.lists),
                icon = Icons.Rounded.List,
                onToListClick = { onWatchListClick() }
            )
            ProfileOptionsCard(
                optionName = stringResource(R.string.sign_out),
                icon = Icons.Rounded.ExitToApp,
                onClick = {
                    onProfileAction(ProfileAction.SignOut(profileUiState.user))
                    onSignOutNavigate()
                },
            )
        }
    }
}