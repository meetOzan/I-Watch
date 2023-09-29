package com.mertozan.moviescompose.presentation.content_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.presentation.main_components.CustomText
import com.mertozan.moviescompose.presentation.content_list.components.ContentItem
import com.mertozan.moviescompose.presentation.content_list.components.EmptyListPlaceholder

@Composable
fun ContentList(
    type: String,
    title: String,
    isLoading: Boolean,
    contentList: List<ContentModel>,
    navController: NavController
) {

    val splashAnimateComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(stringResource(R.string.lottie_list_loading))
    )

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
        if (isLoading) {
            LottieAnimation(
                composition = splashAnimateComposition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                alignment = Alignment.Center
            )
        } else if (contentList.isEmpty()) {
            EmptyListPlaceholder(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.no_favorite_content_added_to_the_list_yet),
                icon = Icons.Filled.Clear
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(contentList) { content ->
                    ContentItem(
                        content = content,
                        navController = navController,
                        type = type
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(72.dp))
    }
}