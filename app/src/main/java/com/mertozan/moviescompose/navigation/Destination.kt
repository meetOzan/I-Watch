package com.mertozan.moviescompose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mertozan.moviescompose.common.Constants.ARGS_ID
import com.mertozan.moviescompose.common.Constants.ARGS_TYPE

interface Destination {
    val route: String
}

object MainScreen : Destination {
    override val route = "main_screen"
}

object DetailScreen : Destination {
    override val route = "detail_screen"
    fun navigateWithArgs(id: Int, type: String): String = "$route/$id/$type"
    val routeWithArgs = "$route/{$ARGS_ID}/{$ARGS_TYPE}"
    val args = listOf(
        navArgument(ARGS_ID) { type = NavType.IntType },
        navArgument(ARGS_TYPE) { type = NavType.StringType }
    )
}

object SplashScreen : Destination {
    override val route = "splash_screen"
}

object LoginScreen : Destination {
    override val route = "login_screen"
}

object ProfileScreen : Destination {
    override val route = "profile_screen"
}

object GenerateScreen : Destination {
    override val route = "generate_screen"
}