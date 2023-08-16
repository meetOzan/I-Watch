package com.mertozan.moviescompose.navigation

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
    fun navigateWithArgs(id: Int, type: String): String = "$route/$id/$type"
    const val argsId = "id"
    const val argsType = "type"
    val routeWithArgs = "$route/{$argsId}/{$argsType}"
    val args = listOf(
        navArgument(argsId) { type = NavType.IntType },
        navArgument(argsType) { type = NavType.StringType }
    )

}