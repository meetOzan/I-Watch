package com.mertozan.moviescompose.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.moviescompose.presantation.detail.ContentList
import com.mertozan.moviescompose.presantation.detail.DetailScreen
import com.mertozan.moviescompose.presantation.detail.DetailViewModel
import com.mertozan.moviescompose.presantation.generate.GenerateContent
import com.mertozan.moviescompose.presantation.generate.GenerateViewModel
import com.mertozan.moviescompose.presantation.home.HomeScreen
import com.mertozan.moviescompose.presantation.home.HomeViewModel
import com.mertozan.moviescompose.presantation.login.LoginScreen
import com.mertozan.moviescompose.presantation.login.LoginViewModel
import com.mertozan.moviescompose.presantation.profile.ProfileScreen
import com.mertozan.moviescompose.presantation.profile.ProfileViewModel
import com.mertozan.moviescompose.presantation.splash.SplashScreen

@Composable
fun MovieNavHost(
    navController: NavHostController,
    context: Context
) {

    NavHost(
        navController = navController, startDestination = SplashScreen.route
    ) {
        splashScreen {
            navController.navigate(LoginScreen.route) {
                popUpTo(SplashScreen.route) {
                    inclusive = true
                }
            }
        }
        loginScreen(context = context, onNavigate = {
            navController.navigate(MainScreen.route) {
                popUpTo(LoginScreen.route) {
                    inclusive = true
                }
                navController.popBackStack(route = MainScreen.route, inclusive = true)
            }
        })
        mainScreen(navController = navController)
        generateScreen()
        profileScreen {
            navController.navigate(LoginScreen.route) {
                navController.popBackStack(route = MainScreen.route, inclusive = true)
            }
        }
        detailScreen {
            navController.navigate(MainScreen.route) {
                popUpTo(DetailScreen.route) {
                    inclusive = true
                }
            }
        }
        contentList(navController = navController)
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
            topRatedMovieList = topRatedMovieList.value,
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

fun NavGraphBuilder.contentList(navController: NavController) {
    composable(
        route = ContentListScreen.routeWithArgs,
        arguments = ContentListScreen.args
    ) {
        val viewModel: HomeViewModel = hiltViewModel()
        val contentList = viewModel.topRatedContents.collectAsState()
        val contentListType = viewModel.contentListType.collectAsState().value
        val contentTitle = viewModel.contentTitle.collectAsState().value

        LaunchedEffect(Unit) {
            viewModel.getContentList()
        }
        ContentList(
            contentList = contentList.value,
            type = contentListType,
            title = contentTitle,
            navController = navController
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

fun NavGraphBuilder.loginScreen(onNavigate: () -> Unit, context: Context) {
    composable(
        route = LoginScreen.route
    ) {
        val loginViewModel = hiltViewModel<LoginViewModel>()

        LoginScreen(
            onNavigate = onNavigate,
            viewModel = loginViewModel,
            context = context
        )
        LoginScreen(onNavigate = onNavigate, viewModel = loginViewModel, context = context)
    }
}

fun NavGraphBuilder.generateScreen() {
    composable(
        route = GenerateScreen.route
    ) {
        val viewModel = hiltViewModel<GenerateViewModel>()

        LaunchedEffect(Unit) {
            viewModel.getAllContents()
            viewModel.shuffleList()
        }

        GenerateContent(viewModel)
    }
}

fun NavGraphBuilder.profileScreen(onNavigate: () -> Unit) {
    composable(
        route = ProfileScreen.route
    ) {
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        val userItem by profileViewModel.user.collectAsState()

        ProfileScreen(
            fullName = userItem.fullName,
            watched = userItem.watched,
            onNavigate = onNavigate,
            onSignOutClick = profileViewModel::signOut,
        )
    }
}