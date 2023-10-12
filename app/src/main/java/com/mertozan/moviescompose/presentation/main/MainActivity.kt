package com.mertozan.moviescompose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.mertozan.moviescompose.data.preferences.DataStorePreference
import com.mertozan.moviescompose.infrastructure.language.AppLanguageProvider
import com.mertozan.moviescompose.presentation.main.components.BottomNavigationView
import com.mertozan.moviescompose.presentation.navigation.MovieNavHost
import com.mertozan.moviescompose.presentation.theme.MoviesComposeTheme
import com.mertozan.moviescompose.util.enums.BottomNavItems
import com.mertozan.moviescompose.util.extensions.SetStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStorePreference: DataStorePreference

    @Inject
    lateinit var appLanguageProvider: AppLanguageProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesComposeTheme {

                SetStatusBarColor(color = Color.Black)

                val navController = rememberNavController()

                setLanguage()

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
                    content = {
                        it
                        MovieNavHost(
                            navController = navController
                        )
                    }
                )
            }
        }
    }

    private fun setLanguage() {
        lifecycleScope.launch {
            dataStorePreference.getSelectedLanguage.flowWithLifecycle(lifecycle)
                .collect { countryCode ->
                    appLanguageProvider.setAppLanguage(countryCode)
                }
        }
    }
}
