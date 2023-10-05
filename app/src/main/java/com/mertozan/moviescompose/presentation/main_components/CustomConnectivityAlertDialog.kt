package com.mertozan.moviescompose.presentation.main_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presentation.theme.DarkRed80
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectivityAlertDialog(
    title: String,
    onDismissClick: () -> Unit = {},
    onOkClicked: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = {
        },
        modifier = Modifier
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = LightBlack
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .background(LightBlack),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(DarkRed80),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.2f)
                        .alpha(0.6f),
                    alignment = Alignment.Center
                )
                CustomText(
                    text = stringResource(R.string.no_connection),
                    color = DarkWhite80,
                    fontSize = 22,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomText(
                    text = stringResource(R.string.internet_status, title),
                    color = DarkWhite80,
                    fontSize = 18,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = {
                        onOkClicked()
                        onDismissClick()
                    }) {
                        CustomText(
                            text = stringResource(R.string.return_to_home),
                            color = DarkYellow
                        )
                    }
                }
            }
        }
    }
}