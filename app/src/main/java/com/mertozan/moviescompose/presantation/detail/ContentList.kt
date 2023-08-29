package com.mertozan.moviescompose.presantation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.presantation.components.components.CustomText
import com.mertozan.moviescompose.presantation.components.items.ContentItem

@Composable
fun ContentList(
    contentList: List<DetailItem>,
    navController: NavController,
    type: String,
    title : String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomText(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(contentList) { content ->
                ContentItem(
                    content = content,
                    navController = navController,
                    type = type
                )
            }
        }
        Spacer(modifier = Modifier.height(72.dp))
    }
}