package com.mertozan.moviescompose.presantation.custom.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.custom.components.CustomText
import com.mertozan.moviescompose.ui.theme.Dark80
import com.mertozan.moviescompose.ui.theme.DarkYellow

@Composable
fun ProfileOptionsCard(
    optionName: String,
    icon: ImageVector,
    onClick : () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Dark80
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CustomText(
                text = optionName,
                fontSize = 20,
                modifier = Modifier.padding(start = 24.dp),
                color = Color.White
            )

            Image(
                imageVector = icon,
                contentDescription = stringResource(R.string.card_icon),
                modifier = Modifier.padding(end = 24.dp),
                colorFilter = ColorFilter.tint(DarkYellow)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionCard() {
    ProfileOptionsCard(icon = Icons.Filled.Favorite, optionName = stringResource(id = R.string.favorites))
}