package com.snappfood.starwars_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.snappfood.starwars_app.model.ScreenDestinations
import com.snappfood.starwars_app.views.detail.DetailScreen
import com.snappfood.starwars_app.views.main.CharacterDetailViewModel
import com.snappfood.starwars_app.views.main.CharacterViewModel
import com.snappfood.starwars_app.views.main.MainScreen
import com.snappfood.starwars_app.views.main.SearchViewModel
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
            ScreenDestinations.DetailScreen.route
        ) {
            val searchViewModel: CharacterDetailViewModel = koinViewModel()
            DetailScreen(
                character = searchViewModel.stateFlow.collectAsState().value
            )
        }
    }
}
