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
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.navigation.ContentListScreen
import com.mertozan.moviescompose.navigation.DetailScreen
import com.mertozan.moviescompose.presantation.custom.layouts.MainColumn
import com.mertozan.moviescompose.presantation.custom.layouts.MainRow
import com.mertozan.moviescompose.util.enums.ListType
import com.mertozan.moviescompose.util.enums.MovieOrSeries

@Composable
fun HomeScreen(
    popularMovieList: List<DetailItem>,
    popularSeriesList: List<DetailItem>,
    topRatedMovieList: List<DetailItem>,
    topRatedSeriesList: List<DetailItem>,
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
            type = MovieOrSeries.MOVIE.name,
            listType = ListType.POPULAR.name,
            onClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        MovieOrSeries.MOVIE.name,
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
            type = MovieOrSeries.SERIES.name,
            listType = ListType.POPULAR.name,
            onClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        MovieOrSeries.SERIES.name,
                        ListType.POPULAR.name
                    )
                )
            },
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(24.dp))
        MainColumn(
            list = topRatedMovieList,
            type = MovieOrSeries.MOVIE.name,
            listType = ListType.TOP_RATED.name,
            onToDetailClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        MovieOrSeries.MOVIE.name,
                        ListType.TOP_RATED.name
                    ),
                )
            },
            onToContentListClick = {
                navController.navigate(
                    ContentListScreen.navigateWithArgs(
                        MovieOrSeries.MOVIE.name
                    )
                )
            },
            title = stringResource(R.string.top_rated_movies)
        )
        Spacer(modifier = Modifier.height(12.dp))
        MainColumn(
            list = topRatedSeriesList,
            type = MovieOrSeries.SERIES.name,
            listType = ListType.TOP_RATED.name,
            onToDetailClick = { id, _, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        MovieOrSeries.SERIES.name,
                        ListType.TOP_RATED.name
                    )
                )
            },
            onToContentListClick = {
                navController.navigate(
                    ContentListScreen.navigateWithArgs(
                        MovieOrSeries.SERIES.name
                    )
                )
            },
            title = stringResource(R.string.top_rated_series),
        )
        Spacer(modifier = Modifier.height(72.dp))
    }
}