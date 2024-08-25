package com.snappfood.starwars_app.navigation

sealed class ScreenDestinations(val route: String) {
    object MainScreen : ScreenDestinations(route = "main_screen")
    object DetailScreen : ScreenDestinations(route = "detail_screen")
}
