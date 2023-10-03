package com.mertozan.moviescompose.presentation.main_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.mertozan.moviescompose.BuildConfig

@Composable
fun CustomAsyncImage(model: String, contentDescription: String, modifier: Modifier,alignment: Alignment) {
    AsyncImage(
        model = "${BuildConfig.POSTER_BASE_PATH}${model}",
        contentDescription = contentDescription,
        modifier = modifier,
        alignment = alignment,
    )
}