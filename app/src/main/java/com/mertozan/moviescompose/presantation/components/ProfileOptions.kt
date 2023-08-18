package com.mertozan.moviescompose.presantation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.ui.theme.Dark80
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily

@Composable
fun ProfileOptionsCard(
    optionName: String,
    icon: ImageVector,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Dark80
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Text(
                text = optionName,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White,
                fontFamily = amazonEmberFamily
            )

            Image(
                imageVector = icon,
                contentDescription = stringResource(R.string.card_icon),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionCard() {
    ProfileOptionsCard(icon = Icons.Filled.Favorite, optionName = "Favorites")
}