package com.phidev.starwars_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.phidev.starwars_app.model.ScreenDestinations
import com.phidev.starwars_app.views.detail.DetailScreen
import com.phidev.starwars_app.views.main.CharacterViewModel
import com.phidev.starwars_app.views.main.MainScreen
import com.phidev.starwars_app.views.main.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen") {
        composable(ScreenDestinations.MainScreen.route) {
            val viewModel: CharacterViewModel = koinViewModel()
            val searchViewModel: SearchViewModel = koinViewModel()
            MainScreen(navController = navController, viewModel , searchViewModel)
        }
        composable(
            ScreenDestinations.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "No Information available"
                    nullable = true
                }
            )
        ) { entry ->
            DetailScreen(
                name = entry.arguments?.getString("name")
            )
        }
    }
}
