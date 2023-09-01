package com.mertozan.moviescompose.presantation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.components.CustomText
import com.mertozan.moviescompose.presantation.theme.Dark80
import com.mertozan.moviescompose.presantation.theme.LightGray70
import com.mertozan.moviescompose.util.extensions.isLongerThan

@Composable
fun MainColumnItem(
    title: String,
    number: Int,
    onNavigate: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigate() }
            .padding(horizontal = 24.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Dark80
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                CustomText(
                    text = "#$number",
                    fontSize = 24,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 20.dp, top = 12.dp, bottom = 12.dp)
                )
                CustomText(
                    text = title.isLongerThan(20),
                    fontSize = 16,
                    color = LightGray70,
                    FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                )
            }
            CustomText(
                text = stringResource(R.string.see_details),
                fontSize = 14,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        onNavigate()
                    }
            )
        }
    }
}