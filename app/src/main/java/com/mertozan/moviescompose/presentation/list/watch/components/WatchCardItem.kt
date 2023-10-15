package com.mertozan.moviescompose.presentation.list.watch.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.presentation.list.watch.viewmodel.WatchListAction
import com.mertozan.moviescompose.presentation.main.components.CustomAlertDialog
import com.mertozan.moviescompose.presentation.main.components.CustomAsyncImage
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.theme.Dark80
import com.mertozan.moviescompose.presentation.theme.DarkRed80
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.util.enums.WatchListType
import com.mertozan.moviescompose.util.extensions.isLongerThan

@Composable
fun WatchCardItem(
    content: ContentModel,
    watchListType: String,
    onWatchListAction: (WatchListAction) -> Unit
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                if (watchListType == WatchListType.WATCHLIST.name) {
                    openDialog = !openDialog
                } else {
                    isExpanded = !isExpanded
                }
            }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Dark80
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(visible = isExpanded) {
                Box(
                    modifier = Modifier
                        .size(
                            height = 150.dp,
                            width = 100.dp
                        )
                        .padding(start = 8.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(DarkRed80)
                        .clickable {
                            onWatchListAction(
                                WatchListAction.RemoveContentFromWatched(
                                    content.id,
                                    content.isWatched,
                                    content.type,
                                    content.listType
                                )
                            )
                            onWatchListAction(
                                WatchListAction.GetAllContents
                            )
                            isExpanded = !isExpanded
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(imageVector = Icons.Filled.Delete, contentDescription = "")
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CustomAsyncImage(
                    model = content.posterPath,
                    contentDescription = stringResource(R.string.custom_content_item_poster),
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .size(
                            height = 150.dp,
                            width = 100.dp
                        )
                        .clip(MaterialTheme.shapes.medium),
                    alignment = Alignment.Center
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.Start
                    ) {
                        CustomText(
                            text = content.title.isLongerThan(if (!isExpanded) 18 else 12),
                            fontSize = 24,
                            color = DarkWhite80,
                            FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                        CustomText(
                            text = content.overview.isLongerThan(if (!isExpanded) 100 else 60),
                            fontSize = 14,
                            color = DarkWhite80,
                            FontWeight.SemiBold,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        CustomText(
                            text = content.voteAverage,
                            fontSize = 16,
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                        Image(
                            imageVector = Icons.Filled.Star,
                            contentDescription = stringResource(R.string.liked),
                            colorFilter = ColorFilter.tint(
                                DarkYellow
                            ),
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                }
            }
        }
    }

    if (openDialog) {
        CustomAlertDialog(
            title = stringResource(id = R.string.did_you_watched),
            body = content.title,
            positiveButtonName = stringResource(id = R.string.i_watched),
            negativeButtonName = stringResource(id = R.string.remove),
            animation = stringResource(R.string.watch_list_pop_up_anim),
            onDismissClick = { openDialog = !openDialog },
            onPositiveAction = {
                onWatchListAction(
                    WatchListAction.TransferToWatched(
                        content.id,
                        content.isInWatchList,
                        content.type,
                        content.listType
                    )
                )
                onWatchListAction(
                    WatchListAction.GetAllContents
                )
                openDialog = !openDialog
            },
            onNegativeAction = {
                onWatchListAction(
                    WatchListAction.RemoveFromWatchList(
                        content.id,
                        content.isInWatchList,
                        content.type,
                        content.listType
                    ),
                )
                onWatchListAction(
                    WatchListAction.GetAllContents
                )
                openDialog = !openDialog
            }
        )
    }
}