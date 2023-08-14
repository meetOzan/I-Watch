package com.mertozan.moviescompose.navigation

interface Destination{
    val route : String
}

object MainScreen : Destination{
    override val route = "main_screen"
}

object DetailScreen : Destination{
    override val route = "detail_screen"

}