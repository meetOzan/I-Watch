package com.mertozan.moviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.moviescompose.presantation.MainScreen
import com.mertozan.moviescompose.presantation.MovieViewModel

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = MainScreen.route
    ) {
        mainScreen()
        // Navigate functions will be added
        detailScreen()
    }
}

fun NavGraphBuilder.mainScreen() {
    composable(MainScreen.route) {
        val viewModel = hiltViewModel<MovieViewModel>()
        MainScreen(
            movieList = viewModel.popularMovies.collectAsState().value,
            seriesList = viewModel.popularSeries.collectAsState().value
        )
    }
}

fun NavGraphBuilder.detailScreen() {
    composable(DetailScreen.route) {
        DetailScreen
    }
}