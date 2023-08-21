package com.mertozan.moviescompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mertozan.moviescompose.common.Constants.ARGS_ID
import com.mertozan.moviescompose.common.Constants.ARGS_TYPE

interface Destination {
    val route: String
    val icon : ImageVector?
    val title : String
}

object MainScreen : Destination {
    override val route = "main_screen"
    override val icon = Icons.Filled.Home
    override val title = "Home"
}

object DetailScreen : Destination {
    override val route = "detail_screen"
    override val icon = null
    override val title = "Detail"
    fun navigateWithArgs(id: Int, type: String): String = "$route/$id/$type"
    val routeWithArgs = "$route/{$ARGS_ID}/{$ARGS_TYPE}"
    val args = listOf(
        navArgument(ARGS_ID) { type = NavType.IntType },
        navArgument(ARGS_TYPE) { type = NavType.StringType }
    )
}

object SplashScreen : Destination{
    override val route = "splash_screen"
    override val icon = null
    override val title = "Splash"
}

object LoginScreen : Destination{
    override val route = "login_screen"
    override val icon = null
    override val title = "Login Screen"
}

object ProfileScreen : Destination{
    override val route = "profile_screen"
    override val icon = Icons.Filled.Person
    override val title = "Profile"
}

object GenerateScreen : Destination {
    override val route = "generate_screen"
    override val icon = Icons.Filled.Build
    override val title = "Prefer"
}