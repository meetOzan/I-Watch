package com.mertozan.moviescompose.settings.component


import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presentation.main_components.CustomText
import com.mertozan.moviescompose.presentation.theme.Dark80
import com.mertozan.moviescompose.presentation.theme.DarkYellow

@Composable
fun SettingsOptionsCard(
    optionName: String,
    icon : Painter,
    isDeepLink: Boolean,
    onClick: () -> Unit = {}
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (isDeepLink){
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/meetozan")
                    )
                    val pendingIntent = TaskStackBuilder.create(context).run {
                        addNextIntentWithParentStack(intent)
                        getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                        )
                    }
                    pendingIntent.send()
                }else{
                    onClick()
                }
            }
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Dark80
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = icon,
                contentDescription = stringResource(R.string.card_icon),
                modifier = Modifier.padding(start = 24.dp).size(36.dp),
                colorFilter = ColorFilter.tint(DarkYellow)
            )

            CustomText(
                text = optionName,
                fontSize = 20,
                modifier = Modifier.padding(end = 24.dp),
                color = Color.White
            )

        }
    }
}