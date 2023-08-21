package com.mertozan.moviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.moviescompose.presantation.detail.DetailScreen
import com.mertozan.moviescompose.presantation.main.MainScreen
import com.mertozan.moviescompose.presantation.generate.GenerateContent
import com.mertozan.moviescompose.presantation.login.LoginScreen
import com.mertozan.moviescompose.presantation.profile.ProfileScreen
import com.mertozan.moviescompose.presantation.splash.SplashScreen
import com.mertozan.moviescompose.presantation.detail.DetailViewModel
import com.mertozan.moviescompose.presantation.main.MovieViewModel

@Composable
fun MovieNavHost(
    navController: NavHostController,
    modifier : Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = SplashScreen.route
    ) {
        splashScreen { navController.navigate(LoginScreen.route) }
        loginScreen { navController.navigate(MainScreen.route) }
        mainScreen(navController = navController)
        generateScreen()
        profileScreen()
        detailScreen { navController.navigate(MainScreen.route) }
    }
}

fun NavGraphBuilder.mainScreen(navController: NavController) {
    composable(route = MainScreen.route) {
        val viewModel = hiltViewModel<MovieViewModel>()
        val movieList = viewModel.popularMovies.collectAsState()
        val seriesList = viewModel.popularSeries.collectAsState()
        MainScreen(
            movieList = movieList.value,
            seriesList = seriesList.value,
            navController = navController
        )
    }
}

fun NavGraphBuilder.detailScreen(onNavigate: () -> Unit) {
    composable(
        route = DetailScreen.routeWithArgs,
        arguments = DetailScreen.args
    ) {
        val viewModel : DetailViewModel = hiltViewModel()
        val detail = viewModel.movieDetailUiState.collectAsState()
        DetailScreen(
            onBackClicked = onNavigate,
            detail = detail.value,
            viewModel = viewModel
        )
    }
}

fun NavGraphBuilder.splashScreen(onNavigate: () -> Unit){
    composable(
        route = SplashScreen.route
    ){
        SplashScreen(onSplashNavigate = onNavigate)
    }
}

fun NavGraphBuilder.loginScreen(onNavigate: () -> Unit){
    composable(
        route = LoginScreen.route
    ){
        LoginScreen(onNavigate)
    }
}

fun NavGraphBuilder.generateScreen(){
    composable(
        route = GenerateScreen.route
    ){
        GenerateContent()
    }
}

fun NavGraphBuilder.profileScreen(){
    composable(
        route = ProfileScreen.route
    ){
        ProfileScreen()
    }
}