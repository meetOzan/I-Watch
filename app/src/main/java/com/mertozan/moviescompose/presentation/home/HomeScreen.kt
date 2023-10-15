package com.mertozan.moviescompose.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mathroda.snackie.SnackieError
import com.mathroda.snackie.rememberSnackieState
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.infrastructure.connectivity.ConnectivityObserver
import com.mertozan.moviescompose.infrastructure.connectivity.NetworkConnectivityObserver
import com.mertozan.moviescompose.presentation.home.components.MainColumn
import com.mertozan.moviescompose.presentation.home.components.MainRow
import com.mertozan.moviescompose.presentation.home.viewmodel.HomeAction
import com.mertozan.moviescompose.presentation.home.viewmodel.HomeUiState
import com.mertozan.moviescompose.presentation.navigation.ContentListScreen
import com.mertozan.moviescompose.presentation.navigation.DetailScreen
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType

@Composable
fun HomeScreen(
    popularMovieList: List<ContentModel>,
    popularSeriesList: List<ContentModel>,
    topRatedMovieList: List<ContentModel>,
    topRatedSeriesList: List<ContentModel>,
    navController: NavController,
    homeUiState: HomeUiState,
    onFavoriteAction: (HomeAction) -> Unit,
) {

    val context = LocalContext.current

    val connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(context)

    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Lost
    )

    val state = rememberSnackieState()

    LaunchedEffect(status) {
        state.addMessage(
            (
                    if (status == ConnectivityObserver.Status.Lost)
                        context.getString(R.string.you_are_offline)
                    else
                        context.getString(R.string.online)
                    )
        )
    }

    Column(
        modifier = Modifier
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {

        if (status != ConnectivityObserver.Status.Available) {
            SnackieError(state = state, duration = 10000L)
        }

        Spacer(modifier = Modifier.height(16.dp))
        MainRow(
            title = stringResource(R.string.top_20_movies_on_this_week),
            list = popularMovieList,
            type = ContentType.MOVIE.name,
            listType = ListType.POPULAR.name,
            onClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentType.MOVIE.name,
                        ListType.POPULAR.name
                    )
                )
            },
            onFavoriteAction = onFavoriteAction,
            homeUiState = homeUiState
        )
        Spacer(modifier = Modifier.height(16.dp))
        MainRow(
            title = stringResource(R.string.top_20_tv_series_on_this_week),
            list = popularSeriesList,
            type = ContentType.SERIES.name,
            listType = ListType.POPULAR.name,
            onClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentType.SERIES.name,
                        ListType.POPULAR.name
                    )
                )
            },
            onFavoriteAction = onFavoriteAction,
            homeUiState = homeUiState
        )
        Spacer(modifier = Modifier.height(24.dp))
        MainColumn(
            list = topRatedMovieList,
            type = ContentType.MOVIE.name,
            listType = ListType.TOP_RATED.name,
            onToDetailClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentType.MOVIE.name,
                        ListType.TOP_RATED.name
                    ),
                )
            },
            onToContentListClick = { _, _ ->
                navController.navigate(
                    ContentListScreen.navigateWithArgs(
                        type = ContentType.MOVIE.name
                    )
                )
            },
            title = stringResource(R.string.top_rated_movies),
            homeUiState = homeUiState
        )
        Spacer(modifier = Modifier.height(12.dp))
        MainColumn(
            list = topRatedSeriesList,
            type = ContentType.SERIES.name,
            listType = ListType.TOP_RATED.name,
            onToDetailClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentType.SERIES.name,
                        ListType.TOP_RATED.name
                    )
                )
            },
            onToContentListClick = { _, _ ->
                navController.navigate(
                    ContentListScreen.navigateWithArgs(
                        type = ContentType.SERIES.name
                    )
                )
            },
            title = stringResource(R.string.top_rated_series),
            homeUiState = homeUiState
        )
        Spacer(modifier = Modifier.height(72.dp))
    }
}