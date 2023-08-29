package com.mertozan.moviescompose.presantation.main

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
import com.mertozan.moviescompose.navigation.DetailScreen
import com.mertozan.moviescompose.presantation.components.MainRow
import com.mertozan.moviescompose.util.enums.MovieOrSeries

@Composable
fun MainScreen(
    movieList: List<DetailItem>,
    seriesList: List<DetailItem>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        MainRow(
            title = stringResource(R.string.top_20_movies_on_this_week),
            list = movieList,
            type = MovieOrSeries.MOVIE.name,
            onClick = { id, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        MovieOrSeries.MOVIE.name
                    )
                )
            }
        )
        MainRow(
            title = stringResource(R.string.top_20_tv_series_on_this_week),
            list = seriesList,
            type = MovieOrSeries.SERIES.name,
            onClick = { id, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        MovieOrSeries.SERIES.name
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}