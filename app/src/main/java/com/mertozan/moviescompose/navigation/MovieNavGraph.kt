package com.mertozan.moviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.moviescompose.presantation.detail.DetailScreen
import com.mertozan.moviescompose.presantation.detail.DetailViewModel
import com.mertozan.moviescompose.presantation.generate.GenerateContent
import com.mertozan.moviescompose.presantation.generate.GenerateViewModel
import com.mertozan.moviescompose.presantation.login.LoginScreen
import com.mertozan.moviescompose.presantation.login.LoginViewModel
import com.mertozan.moviescompose.presantation.home.HomeScreen
import com.mertozan.moviescompose.presantation.home.HomeViewModel
import com.mertozan.moviescompose.presantation.profile.ProfileScreen
import com.mertozan.moviescompose.presantation.profile.ProfileViewModel
import com.mertozan.moviescompose.presantation.splash.SplashScreen

@Composable
fun MovieNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = SplashScreen.route
    ) {
        splashScreen { navController.navigate(LoginScreen.route) }
        loginScreen { navController.navigate(MainScreen.route) }
        mainScreen(navController = navController)
        generateScreen(navController = navController)
        profileScreen { navController.navigate(LoginScreen.route) }
        detailScreen { navController.navigate(MainScreen.route) }
    }
}

fun NavGraphBuilder.mainScreen(navController: NavController) {
    composable(route = MainScreen.route) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val popularMovieList = viewModel.popularMovies.collectAsState()
        val popularSeriesList = viewModel.popularSeries.collectAsState()
        val topRatedMovieList = viewModel.topRatedMovies.collectAsState()
        val topRatedSeriesList = viewModel.topRatedSeries.collectAsState()
        HomeScreen(
            popularMovieList = popularMovieList.value,
            popularSeriesList = popularSeriesList.value,
            topRatedMovieList  = topRatedMovieList.value,
            topRatedSeriesList = topRatedSeriesList.value,
            navController = navController,
            viewModel = viewModel
        )
    }
}

fun NavGraphBuilder.detailScreen(onNavigate: () -> Unit) {
    composable(
        route = DetailScreen.routeWithArgs,
        arguments = DetailScreen.args
    ) {
        val viewModel: DetailViewModel = hiltViewModel()
        val detail = viewModel.movieDetailUiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getDetail()
        }

        DetailScreen(
            onBackClicked = onNavigate,
            detail = detail.value,
            viewModel = viewModel
        )
    }
}

fun NavGraphBuilder.splashScreen(onNavigate: () -> Unit) {
    composable(
        route = SplashScreen.route
    ) {
        SplashScreen(onSplashNavigate = onNavigate)
    }
}

fun NavGraphBuilder.loginScreen(onNavigate: () -> Unit) {
    composable(
        route = LoginScreen.route
    ) {
        val loginViewModel = hiltViewModel<LoginViewModel>()

        LoginScreen(onNavigate, loginViewModel)
    }
}

fun NavGraphBuilder.generateScreen(navController: NavController) {
    composable(
        route = GenerateScreen.route
    ) {
        val viewModel = hiltViewModel<GenerateViewModel>()
        viewModel.getAllContents()
        GenerateContent(viewModel, navController = navController)
    }
}

fun NavGraphBuilder.profileScreen(onNavigate: () -> Unit) {
    composable(
        route = ProfileScreen.route
    ) {
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        ProfileScreen(
            onNavigate = onNavigate,
            viewModel = profileViewModel
        )
    }
}