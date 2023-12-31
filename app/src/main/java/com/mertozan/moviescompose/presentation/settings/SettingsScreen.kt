package com.mertozan.moviescompose.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presentation.main.components.CustomAlertDialog
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.profile.viewmodel.ProfileAction
import com.mertozan.moviescompose.presentation.profile.viewmodel.ProfileUiState
import com.mertozan.moviescompose.presentation.settings.component.SettingsOptionsCard

@Composable
fun SettingsScreen(
    profileUiState: ProfileUiState,
    onSignOutNavigate: () -> Unit,
    onProfileAction: (ProfileAction) -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

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
                optionName = stringResource(R.string.source_code),
                icon = painterResource(id = R.drawable.github_icon),
                true
            )
            SettingsOptionsCard(
                optionName = stringResource(R.string.sign_out),
                icon = rememberVectorPainter(Icons.Rounded.ExitToApp),
                false,
                onClick = {
                    openDialog = !openDialog
                }
            )
        }
    }

    if (openDialog) {
        CustomAlertDialog(
            title = stringResource(id = R.string.are_you_sure_you_want_to_log_out),
            body = stringResource(id = R.string.sign_out_message),
            positiveButtonName = stringResource(R.string.yes_i_m_sure),
            negativeButtonName = stringResource(R.string.no_i_ll_think_it_over),
            onPositiveAction = {
                onProfileAction(ProfileAction.SignOut(profileUiState.user))
                onSignOutNavigate()
            },
            onNegativeAction = { openDialog = !openDialog },
            onDismissClick = { openDialog = !openDialog }
        )
    }
}