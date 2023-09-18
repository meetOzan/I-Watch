package com.mertozan.moviescompose.presantation.watch_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.presantation.content_list.components.EmptyListPlaceholder
import com.mertozan.moviescompose.presantation.main_components.CustomText
import com.mertozan.moviescompose.presantation.theme.DarkYellow
import com.mertozan.moviescompose.presantation.theme.LightBlack
import com.mertozan.moviescompose.presantation.theme.LightGray70
import com.mertozan.moviescompose.presantation.watch_list.components.TabIndicator
import com.mertozan.moviescompose.presantation.watch_list.components.WatchCardItem
import com.mertozan.moviescompose.presantation.watch_list.viewmodel.WatchListAction
import com.mertozan.moviescompose.util.enums.WatchListType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WatchListScreen(
    isInWatchList: List<ContentModel>,
    isWatchedList: List<ContentModel>,
    onWatchListAction: (WatchListAction) -> Unit
) {

    Surface {

        var selectedTab by remember {
            mutableIntStateOf(0)
        }

        val pagerState = rememberPagerState(pageCount = { WatchListType.values().size })

        LaunchedEffect(selectedTab) {
            pagerState.animateScrollToPage(selectedTab)
        }

        LaunchedEffect(pagerState.currentPage) {
            selectedTab = pagerState.currentPage
        }

        Scaffold(
            topBar = {
                TabRow(
                    selectedTabIndex = selectedTab,
                    indicator = { TabIndicator(tabPosition = it, index = selectedTab) }
                ) {
                    WatchListType.values().forEachIndexed { index, tabPage ->
                        Tab(
                            selected = index == selectedTab,
                            onClick = { selectedTab = index },
                            text = { Text(text = tabPage.name) },
                            modifier = Modifier.background(LightBlack),
                            selectedContentColor = DarkYellow,
                            unselectedContentColor = LightGray70,
                        )
                    }
                }
            },
            content = {
                it
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                ) {
                    HorizontalPager(
                        state = pagerState,
                    ) { page ->
                        when (page) {

                            0 -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(vertical = 50.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CustomText(
                                        text = stringResource(R.string.watch_list),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 24,
                                        modifier = Modifier.padding(vertical = 16.dp)
                                    )
                                    if (isInWatchList.isEmpty()) {
                                        EmptyListPlaceholder(
                                            text = stringResource(R.string.you_haven_t_added_content_to_your_watch_list_yet_add_content_to_your_watchlist),
                                            modifier = Modifier.weight(1f),
                                            icon = Icons.Filled.Notifications
                                        )
                                    } else {
                                        LazyColumn(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            items(isInWatchList) { content ->
                                                WatchCardItem(
                                                    content = content,
                                                    onWatchListAction = onWatchListAction,
                                                    watchListType = WatchListType.WATCHLIST.name
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            1 -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(vertical = 50.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CustomText(
                                        text = stringResource(R.string.watched_list),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 24,
                                        modifier = Modifier.padding(vertical = 16.dp)
                                    )
                                    if (isWatchedList.isEmpty()) {
                                        EmptyListPlaceholder(
                                            text = stringResource(R.string.you_ve_never_seen_a_movie_you_can_add_the_movies_you_ve_seen_here),
                                            modifier = Modifier.weight(1f),
                                            icon = Icons.Filled.Warning
                                        )
                                    } else {
                                        LazyColumn(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            items(isWatchedList) { content ->
                                                WatchCardItem(
                                                    content = content,
                                                    onWatchListAction = onWatchListAction,
                                                    watchListType = WatchListType.WATCHED.name
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}