package com.mertozan.moviescompose.presantation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object MainScreen : Destination {
    override val route = "main_screen"
}

object DetailScreen : Destination {
    override val route = "detail_screen"
    fun navigateWithArgs(
        id: Int, type: String, listType: String
    ): String = "$route/$id/$type/$listType"

    val routeWithArgs = "$route/{$ARGS_ID}/{$ARGS_TYPE}/{$ARGS_CONTENT_TYPE}"
    val args = listOf(
        navArgument(ARGS_ID) { type = NavType.IntType },
        navArgument(ARGS_TYPE) { type = NavType.StringType },
        navArgument(ARGS_CONTENT_TYPE) { type = NavType.StringType }
    )
}

object ContentListScreen : Destination {
    override val route = "content_list_screen"
    fun navigateWithArgs(
        type: String,
    ): String = "$route/$type"

    val routeWithArgs = "$route/{$ARGS_TYPE}"
    val args = listOf(
        navArgument(ARGS_TYPE) { type = NavType.StringType },
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

const val ARGS_ID = "id"

const val ARGS_TYPE = "type"

const val ARGS_CONTENT_TYPE = "list_type"