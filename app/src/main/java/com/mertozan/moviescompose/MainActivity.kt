package com.mertozan.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mertozan.moviescompose.navigation.MovieNavHost
import com.mertozan.moviescompose.ui.theme.MoviesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesComposeTheme {
                val navController = rememberNavController()

                MovieNavHost(navController = navController)
            }
        }
    }
}