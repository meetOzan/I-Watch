package com.mertozan.moviescompose.presantation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.navigation.DetailScreen
import com.mertozan.moviescompose.presantation.components.MainRow
import com.mertozan.moviescompose.presantation.viewmodel.MovieViewModel

@Composable
fun MainScreen(
    movieList: List<DetailItem>,
    seriesList: List<DetailItem>,
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        MainRow(
            title = stringResource(R.string.top_20_movies_on_this_week),
            list = movieList,
            type = MovieOrSeries.MOVIE.name,
            viewModel = viewModel,
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
            viewModel = viewModel,
            onClick = { id, _ ->
                navController.navigate(
                    DetailScreen.navigateWithArgs(
                        id,
                        MovieOrSeries.SERIES.name
                    )
                )
            }
        )
    }
}

enum class MovieOrSeries {
    MOVIE,
    SERIES
}