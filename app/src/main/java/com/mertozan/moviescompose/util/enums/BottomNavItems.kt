package com.mertozan.moviescompose.util.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.mertozan.moviescompose.presentation.navigation.GenerateScreen
import com.mertozan.moviescompose.presentation.navigation.MainScreen
import com.mertozan.moviescompose.presentation.navigation.ProfileScreen

enum class BottomNavItems(val route: String, val icon: ImageVector?, val label: String) {
    MAIN_SCREEN(route = MainScreen.route, icon = Icons.Filled.Home, label = "Home"),
    GENERATE(route = GenerateScreen.route, icon = Icons.Filled.PlayArrow, label = "Prefer"),
    PROFILE(route = ProfileScreen.route, icon = Icons.Filled.Person, label = "Profile")
}