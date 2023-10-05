package com.mertozan.moviescompose.presentation.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.mertozan.moviescompose.presentation.auth.LoginScreen
import com.mertozan.moviescompose.presentation.auth.viewmodel.AuthViewModel
import com.mertozan.moviescompose.presentation.list.content.ContentList
import com.mertozan.moviescompose.presentation.list.content.viewmodel.ListViewModel
import com.mertozan.moviescompose.presentation.detail.DetailScreen
import com.mertozan.moviescompose.presentation.detail.viewmodel.DetailAction
import com.mertozan.moviescompose.presentation.detail.viewmodel.DetailViewModel
import com.mertozan.moviescompose.presentation.generate.GenerateContent
import com.mertozan.moviescompose.presentation.generate.viewmodel.GenerateAction
import com.mertozan.moviescompose.presentation.generate.viewmodel.GenerateViewModel
import com.mertozan.moviescompose.presentation.home.HomeScreen
import com.mertozan.moviescompose.presentation.home.viewmodel.HomeViewModel
import com.mertozan.moviescompose.presentation.profile.ProfileScreen
import com.mertozan.moviescompose.presentation.profile.viewmodel.ProfileViewModel
import com.mertozan.moviescompose.presentation.settings.SettingsScreen
import com.mertozan.moviescompose.presentation.splash.SplashScreen
import com.mertozan.moviescompose.presentation.list.watch.WatchListScreen
import com.mertozan.moviescompose.presentation.list.watch.viewmodel.WatchListViewModel

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
        settingScreen()
        mainScreen(navController = navController)
        generateScreen(
            onNavigate = {
                navController.navigate(MainScreen.route)
            }
        )
        profileScreen(
            navController = navController,
            onSignOutNavigate = {
                navController.navigate(LoginScreen.route) {
                    navController.popBackStack(route = MainScreen.route, inclusive = true)
                }
            },
            onWatchListClick = {
                navController.navigate(WatchListScreen.route)
            },
            onSettingsClick = {
                navController.navigate(SettingsScreen.route)
            }
        )
        watchListScreen()
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
        val isLoading = listUiState.favoriteIsLoading

        LaunchedEffect(Unit) {
            viewModel.getContentList()
        }

        ContentList(
            contentList = contentList,
            isLoading = isLoading,
            type = contentListType,
            title = contentTitle,
            navController = navController
        )
    }
}

fun NavGraphBuilder.watchListScreen() {
    composable(
        route = WatchListScreen.route
    ) {
        val watchListViewModel: WatchListViewModel = hiltViewModel()
        val isWatchedList =
            watchListViewModel.watchListUiState.collectAsState().value.isWatchedList
        val isInWatchedList =
            watchListViewModel.watchListUiState.collectAsState().value.isInWatchedList

        WatchListScreen(
            isInWatchList = isInWatchedList,
            isWatchedList = isWatchedList,
            onWatchListAction = watchListViewModel::onAction
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
        val loginViewModel = hiltViewModel<AuthViewModel>()
        val userModel = loginViewModel.userItem.collectAsState().value
        val userCurrent = loginViewModel.authUiState.collectAsState().value.checkCurrentUser
        val errorMessage = loginViewModel.authUiState.collectAsState().value.exceptionMessage

        LoginScreen(
            userModel = userModel,
            userCurrent = userCurrent,
            errorMessage = errorMessage,
            onNavigate = onNavigate,
            onAuthAction = loginViewModel::onAction,
        )
    }
}

fun NavGraphBuilder.generateScreen(onNavigate: () -> Unit) {
    composable(
        route = GenerateScreen.route,
    ) {
        val viewModel = hiltViewModel<GenerateViewModel>()
        val generateUiState = viewModel.generateUiState.collectAsState().value
        var generateList = generateUiState.allContents

        LaunchedEffect(Unit) {
            viewModel.getContents()
        }

        LaunchedEffect(Unit) {
            viewModel.onAction(GenerateAction.ShuffleList)
            generateList = viewModel.generateUiState.value.allContents
        }

        GenerateContent(
            trendList = generateList,
            onGenerateAction = viewModel::onAction,
            generateUiState = generateUiState,
            onOkClicked = onNavigate
        )
    }
}

fun NavGraphBuilder.profileScreen(
    navController: NavController,
    onSignOutNavigate: () -> Unit,
    onWatchListClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    composable(
        route = ProfileScreen.route
    ) {
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        val profileUiState = profileViewModel.profileUiState.collectAsState().value
        val userItem = profileUiState.user

        ProfileScreen(
            fullName = userItem.fullName,
            watched = profileUiState.watchCount,
            profileUiState = profileUiState,
            navController = navController,
            onSignOutNavigate = onSignOutNavigate,
            onSignOutClick = profileViewModel::signOut,
            onWatchListClick = onWatchListClick,
            onSettingsClick = onSettingsClick
        )
    }
}

fun NavGraphBuilder.settingScreen() {
    composable(
        route = SettingsScreen.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://github.com/meetOzan"
                action = Intent.ACTION_VIEW
            }
        )
    ) {
        SettingsScreen()
    }
}