package com.mertozan.moviescompose.presantation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.mertozan.moviescompose.presantation.navigation.MovieNavHost
import com.mertozan.moviescompose.presantation.theme.MoviesComposeTheme
import com.mertozan.moviescompose.util.enums.BottomNavItems
import com.mertozan.moviescompose.util.extensions.SetStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesComposeTheme {

                SetStatusBarColor(color = Color.Black)
                
                val navController = rememberNavController()

                val items = listOf(
                    BottomNavItems.MAIN_SCREEN,
                    BottomNavItems.GENERATE,
                    BottomNavItems.PROFILE,
                )

                Scaffold(
                    bottomBar = {
                        BottomNavigationView(
                            items = items,
                            navController = navController
                        )
                    },
                    content = { it
                        MovieNavHost(
                            navController = navController
                        )
                    }
                )
            }
        }
    }
}
