package com.mertozan.moviescompose.presantation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.moviescompose.presantation.auth.LoginScreen
import com.mertozan.moviescompose.presantation.auth.viewmodel.LoginViewModel
import com.mertozan.moviescompose.presantation.detail.DetailScreen
import com.mertozan.moviescompose.presantation.detail.viewmodel.DetailAction
import com.mertozan.moviescompose.presantation.detail.viewmodel.DetailViewModel
import com.mertozan.moviescompose.presantation.generate.GenerateContent
import com.mertozan.moviescompose.presantation.generate.viewmodel.GenerateAction
import com.mertozan.moviescompose.presantation.generate.viewmodel.GenerateViewModel
import com.mertozan.moviescompose.presantation.home.HomeScreen
import com.mertozan.moviescompose.presantation.home.viewmodel.HomeViewModel
import com.mertozan.moviescompose.presantation.list.ContentList
import com.mertozan.moviescompose.presantation.list.viewmodel.ListViewModel
import com.mertozan.moviescompose.presantation.profile.ProfileScreen
import com.mertozan.moviescompose.presantation.profile.viewmodel.ProfileViewModel
import com.mertozan.moviescompose.presantation.splash.SplashScreen

@Composable
fun MovieNavHost(
    navController: NavHostController
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
        loginScreen(onNavigate = {
            navController.navigate(MainScreen.route) {
                popUpTo(LoginScreen.route) {
                    inclusive = true
                }
            }
        })
        mainScreen(navController = navController)
        generateScreen()
        profileScreen(
            onNavigate = {
                navController.navigate(LoginScreen.route) {
                    navController.popBackStack(route = MainScreen.route, inclusive = true)
                }
            },
            navController = navController
        )
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
        val homeUiState = viewModel.homeUiState.collectAsState().value
        val popularMovieList = homeUiState.popularMovies
        val popularSeriesList = homeUiState.popularSeries
        val topRatedMovieList = homeUiState.topRatedMovies
        val topRatedSeriesList = homeUiState.topRatedSeries
        HomeScreen(
            popularMovieList = popularMovieList,
            popularSeriesList = popularSeriesList,
            topRatedMovieList = topRatedMovieList,
            topRatedSeriesList = topRatedSeriesList,
            navController = navController,
            onFavoriteAction = viewModel::onAction,
            homeUiState = homeUiState
        )
    }
}

fun NavGraphBuilder.detailScreen(onNavigate: () -> Unit) {
    composable(
        route = DetailScreen.routeWithArgs,
        arguments = DetailScreen.args
    ) {
        val viewModel: DetailViewModel = hiltViewModel()
        val detail = viewModel.uiState.collectAsState().value.movieDetailUiState

        LaunchedEffect(Unit) {
            viewModel.onAction(DetailAction.GetSingleDetail)
        }

        DetailScreen(
            onBackClicked = onNavigate,
            detail = detail,
            onUpdateAction = viewModel::onAction
        )
    }
}

fun NavGraphBuilder.contentList(navController: NavController) {
    composable(
        route = ContentListScreen.routeWithArgs,
        arguments = ContentListScreen.args
    ) {
        val viewModel: ListViewModel = hiltViewModel()
        val listUiState = viewModel.listUiState.collectAsState().value
        val contentList = listUiState.contentList
        val contentListType = listUiState.contentListType
        val contentTitle = listUiState.contentTitle

        LaunchedEffect(Unit) {
            viewModel.getContentList()
        }

        ContentList(
            contentList = contentList,
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

fun NavGraphBuilder.loginScreen(onNavigate: () -> Unit) {
    composable(
        route = LoginScreen.route
    ) {
        val loginViewModel = hiltViewModel<LoginViewModel>()

        LoginScreen(
            onNavigate = onNavigate,
            viewModel = loginViewModel
        )
    }
}

fun NavGraphBuilder.generateScreen() {
    composable(
        route = GenerateScreen.route
    ) {
        val viewModel = hiltViewModel<GenerateViewModel>()
        val generateUiState = viewModel.generateUiState.collectAsState().value
        var generateList = generateUiState.allContents

        LaunchedEffect(Unit) {
            viewModel.getAllContents()
            viewModel.onAction(GenerateAction.ShuffleList)
            generateList = viewModel.generateUiState.value.allContents
        }

        GenerateContent(
            trendList = generateList,
            onShuffleAction = viewModel::onAction,
            generateUiState = generateUiState
        )
    }
}

fun NavGraphBuilder.profileScreen(onNavigate: () -> Unit, navController: NavController) {
    composable(
        route = ProfileScreen.route
    ) {
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        val profileUiState = profileViewModel.profileUiState.collectAsState().value
        val userItem = profileUiState.user

        ProfileScreen(
            fullName = userItem.fullName,
            watched = userItem.watched,
            onNavigate = onNavigate,
            onSignOutClick = profileViewModel::signOut,
            profileUiState = profileUiState,
            navController = navController
        )
    }
}