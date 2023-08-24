package com.mertozan.moviescompose.presantation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.presantation.main.HomeViewModel
import com.mertozan.moviescompose.ui.theme.LightBlack

@Composable
fun MainRow(
    title: String,
    list: List<DetailItem>,
    type: String,
    onClick: (Int, String) -> Unit,
    viewModel: HomeViewModel
) {

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Divider(
            modifier = Modifier
                .height(25.dp)
                .width(3.5.dp),
            color = Color.Yellow
        )
        CustomText(
            text = title,
            fontSize = 24,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
    LazyRow(
        modifier = Modifier
            .background(LightBlack)
            .padding(bottom = 8.dp)
    ) {
        items(list) { content ->
            MovieItem(
                onCardClick = {
                    onClick(content.id, type)
                },
                content = content,
                number = (list.indexOf(content)) + 1,
                viewModel = viewModel,
                type = type
            )
        }
    }
}