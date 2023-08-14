package com.mertozan.moviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.moviescompose.presantation.DetailScreen
import com.mertozan.moviescompose.presantation.MainScreen
import com.mertozan.moviescompose.presantation.viewmodel.MovieViewModel

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = MainScreen.route
    ) {
        mainScreen(navController = navController)
        detailScreen { navController.navigate(MainScreen.route) }
    }
}

fun NavGraphBuilder.mainScreen(navController: NavController) {
    composable(MainScreen.route) {
        val viewModel = hiltViewModel<MovieViewModel>()
        MainScreen(
            movieList = viewModel.popularMovies.collectAsState().value,
            seriesList = viewModel.popularSeries.collectAsState().value,
            navController = navController
        )
    }
}

fun NavGraphBuilder.detailScreen(onNavigate: () -> Unit) {
    composable("${DetailScreen.route}/{id}/{type}") { backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id")
        val type = backStackEntry.arguments?.getString("type")
        DetailScreen(
            onBackClicked = onNavigate,
            id = id,
            type = type
        )
    }
}