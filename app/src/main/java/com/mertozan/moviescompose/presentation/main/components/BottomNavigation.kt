package com.mertozan.moviescompose.presentation.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mertozan.moviescompose.presentation.navigation.ContentListScreen
import com.mertozan.moviescompose.presentation.navigation.DetailScreen
import com.mertozan.moviescompose.presentation.navigation.GenerateScreen
import com.mertozan.moviescompose.presentation.navigation.LoginScreen
import com.mertozan.moviescompose.presentation.navigation.MainScreen
import com.mertozan.moviescompose.presentation.navigation.ProfileScreen
import com.mertozan.moviescompose.presentation.navigation.SettingsScreen
import com.mertozan.moviescompose.presentation.navigation.SplashScreen
import com.mertozan.moviescompose.presentation.navigation.WatchListScreen
import com.mertozan.moviescompose.presentation.theme.Dark80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.util.enums.BottomNavItems

@Composable
fun BottomNavigationView(items: List<BottomNavItems>, navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    when (navBackStackEntry?.destination?.route) {

        SplashScreen.route -> {
            bottomBarState.value = false
        }

        LoginScreen.route -> {
            bottomBarState.value = false
        }

        DetailScreen.route -> {
            bottomBarState.value = false
        }

        ContentListScreen.route -> {
            bottomBarState.value = true
        }

        WatchListScreen.route -> {
            bottomBarState.value = false
        }

        SettingsScreen.route -> {
            bottomBarState.value = false
        }

        MainScreen.route -> {
            bottomBarState.value = true
        }

        ProfileScreen.route -> {
            bottomBarState.value = true
        }

        GenerateScreen.route -> {
            bottomBarState.value = true
        }
    }

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigation(
            backgroundColor = DarkYellow,
            contentColor = Dark80
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    label = {
                        Text(
                            text = item.label,
                            fontSize = 9.sp
                        )
                    },
                    icon = {
                        item.icon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = item.label
                            )
                        }
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}