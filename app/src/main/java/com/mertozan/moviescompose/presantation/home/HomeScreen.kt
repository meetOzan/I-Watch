package com.mertozan.moviescompose.presantation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.presantation.home.components.MainRow
import com.mertozan.moviescompose.presantation.home.components.MainColumn
import com.mertozan.moviescompose.presantation.navigation.ContentListScreen
import com.mertozan.moviescompose.presantation.navigation.DetailScreen
import com.mertozan.moviescompose.util.enums.ContentTypes
import com.mertozan.moviescompose.util.enums.ListType

@Composable
fun HomeScreen(
    popularMovieList: List<ContentModel>,
    popularSeriesList: List<ContentModel>,
    topRatedMovieList: List<ContentModel>,
    topRatedSeriesList: List<ContentModel>,
    navController: NavController,
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        MainRow(
            title = stringResource(R.string.top_20_movies_on_this_week),
            list = popularMovieList,
            type = ContentTypes.MOVIE.name,
            listType = ListType.POPULAR.name,
            onClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentTypes.MOVIE.name,
                        ListType.POPULAR.name
                    )
                )
            },
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(16.dp))
        MainRow(
            title = stringResource(R.string.top_20_tv_series_on_this_week),
            list = popularSeriesList,
            type = ContentTypes.SERIES.name,
            listType = ListType.POPULAR.name,
            onClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentTypes.SERIES.name,
                        ListType.POPULAR.name
                    )
                )
            },
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(24.dp))
        MainColumn(
            list = topRatedMovieList,
            type = ContentTypes.MOVIE.name,
            listType = ListType.TOP_RATED.name,
            onToDetailClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentTypes.MOVIE.name,
                        ListType.TOP_RATED.name
                    ),
                )
            },
            onToContentListClick = {
                navController.navigate(
                    ContentListScreen.navigateWithArgs(
                        ContentTypes.MOVIE.name
                    )
                )
            },
            title = stringResource(R.string.top_rated_movies)
        )
        Spacer(modifier = Modifier.height(12.dp))
        MainColumn(
            list = topRatedSeriesList,
            type = ContentTypes.SERIES.name,
            listType = ListType.TOP_RATED.name,
            onToDetailClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        ContentTypes.SERIES.name,
                        ListType.TOP_RATED.name
                    )
                )
            },
            onToContentListClick = {
                navController.navigate(
                    ContentListScreen.navigateWithArgs(
                        ContentTypes.SERIES.name
                    )
                )
            },
            title = stringResource(R.string.top_rated_series),
        )
        Spacer(modifier = Modifier.height(72.dp))
    }
}