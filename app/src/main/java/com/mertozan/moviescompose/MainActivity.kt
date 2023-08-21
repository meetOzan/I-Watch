package com.mertozan.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mertozan.moviescompose.navigation.DetailScreen
import com.mertozan.moviescompose.navigation.GenerateScreen
import com.mertozan.moviescompose.navigation.LoginScreen
import com.mertozan.moviescompose.navigation.MainScreen
import com.mertozan.moviescompose.navigation.MovieNavHost
import com.mertozan.moviescompose.navigation.ProfileScreen
import com.mertozan.moviescompose.navigation.SplashScreen
import com.mertozan.moviescompose.ui.theme.Dark80
import com.mertozan.moviescompose.ui.theme.DarkYellow
import com.mertozan.moviescompose.ui.theme.MoviesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesComposeTheme {

                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavigationView(navController = navController) },
                    content = { padding ->
                        MovieNavHost(
                            navController = navController,
                            modifier = Modifier.padding(padding)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationView(navController: NavController) {
    val items = listOf(
        MainScreen,
        GenerateScreen,
        ProfileScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarState = rememberSaveable { (mutableStateOf(true))}

    when (navBackStackEntry?.destination?.route) {
        SplashScreen.route-> {
            bottomBarState.value = false
        }
        LoginScreen.route -> {
            bottomBarState.value = false
        }
        DetailScreen.route -> {
            bottomBarState.value = true
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
        exit = slideOutVertically(targetOffsetY = { it })) {
        BottomNavigation(
            backgroundColor = DarkYellow,
            contentColor = Dark80
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    },
                    icon = {
                        item.icon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = item.title
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