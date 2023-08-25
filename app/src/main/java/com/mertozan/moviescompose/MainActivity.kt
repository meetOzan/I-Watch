package com.mertozan.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mertozan.moviescompose.navigation.MovieNavHost
import com.mertozan.moviescompose.presantation.components.layouts.BottomNavigationView
import com.mertozan.moviescompose.ui.theme.MoviesComposeTheme
import com.mertozan.moviescompose.util.enums.BottomNavItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesComposeTheme {

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
