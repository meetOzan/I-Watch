package com.mertozan.moviescompose.presantation.main_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.theme.DarkWhite80
import com.mertozan.moviescompose.presantation.theme.DarkYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    title: String,
    onDismissClick: () -> Unit,
    onDoWatched: () -> Unit,
    onRemoveFromList : () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismissClick()
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = Color.Black
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .background(Color.Black),

                ) {
                CustomText(
                    text = stringResource(R.string.did_you_watched, title),
                    color = DarkWhite80,
                    fontSize = 22,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { onDoWatched() }) {
                        CustomText(text = stringResource(R.string.i_watched), color = DarkYellow)
                    }

                    TextButton(onClick = { onRemoveFromList() }) {
                        CustomText(text = stringResource(R.string.remove), color = DarkYellow)
                    }
                }
            }
        }
    }
}