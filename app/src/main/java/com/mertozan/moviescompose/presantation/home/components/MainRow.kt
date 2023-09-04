package com.mertozan.moviescompose.presantation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.presantation.components.CustomText
import com.mertozan.moviescompose.presantation.home.viewmodel.HomeAction
import com.mertozan.moviescompose.presantation.home.viewmodel.HomeUiState
import com.mertozan.moviescompose.presantation.theme.LightBlack

@Composable
fun MainRow(
    title: String,
    list: List<ContentModel>,
    type: String,
    listType: String,
    onClick: (Int, String, String) -> Unit,
    onFavoriteAction: (HomeAction) -> Unit,
    homeUiState: HomeUiState
) {

    val splashAnimateComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(stringResource(R.string.lottie_row_loading))
    )

    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
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
    if (homeUiState.isLoading) {
        LottieAnimation(
            composition = splashAnimateComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            alignment = Alignment.Center
        )
    } else if (list.isEmpty()) {
        LottieAnimation(
            composition = splashAnimateComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            alignment = Alignment.Center
        )
    } else {
        LazyRow(
            modifier = Modifier
                .background(LightBlack)
                .padding(bottom = 8.dp)
        ) {
            items(list) { content ->
                MainRowCardItem(
                    onCardClick = {
                        onClick(content.id, type, listType)
                    },
                    content = content,
                    number = (list.indexOf(content)).plus(1),
                    type = type,
                    onFavoriteAction = onFavoriteAction
                )
            }
        }
    }
}