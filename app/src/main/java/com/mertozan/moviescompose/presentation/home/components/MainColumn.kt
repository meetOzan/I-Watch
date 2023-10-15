package com.mertozan.moviescompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.home.components.ContentList.CONTENT_LIST
import com.mertozan.moviescompose.presentation.home.viewmodel.HomeUiState
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack
import com.mertozan.moviescompose.util.enums.ContentListType
import com.mertozan.moviescompose.util.enums.ContentType

@Composable
fun MainColumn(
    list: List<ContentModel>,
    type: String,
    listType: String,
    title: String,
    homeUiState: HomeUiState,
    onToDetailClick: (Int, String, String) -> Unit,
    onToContentListClick: (String,String) -> Unit,
) {

    val splashAnimateComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(stringResource(R.string.lottie_list_loading))
    )

    val isLoading : Boolean =
        if (type == ContentType.MOVIE.name) homeUiState.topMoviesIsLoading
        else homeUiState.topSeriesIsLoading

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.weight(1f)
        ) {
            Divider(
                modifier = Modifier
                    .height(21.dp)
                    .width(3.dp),
                color = DarkYellow
            )
            CustomText(
                text = title,
                fontSize = 20,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        CustomText(
            text = stringResource(R.string.see_more),
            fontSize = 18,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier.clickable {
                if (list.isNotEmpty()) {
                    onToContentListClick(
                        type,
                        ContentListType.TOP_CONTENTS.name
                    )
                }
            }
        )
    }
    if (isLoading) {
        LottieAnimation(
            composition = splashAnimateComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
    }  else {
        LazyColumn(
            modifier = Modifier
                .background(LightBlack)
                .height(250.dp)
        ) {
            itemsIndexed(list) { index, content ->
                if (index < CONTENT_LIST) {
                    MainColumnItem(
                        title = content.title,
                        number = (list.indexOf(content)).plus(1),
                        onNavigate = {
                            onToDetailClick(content.id, type, listType)
                        }
                    )
                }
            }
        }
    }
}

object ContentList {
    const val CONTENT_LIST = 10
}