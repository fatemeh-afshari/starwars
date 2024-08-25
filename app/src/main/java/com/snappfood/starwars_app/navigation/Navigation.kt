package com.snappfood.starwars_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.snappfood.starwars_app.views.detail.DetailScreen
import com.snappfood.starwars_app.views.detail.viewmodel.CharacterDetailViewModel
import com.snappfood.starwars_app.views.main.viewmodel.CharacterViewModel
import com.snappfood.starwars_app.views.main.MainScreen
import com.snappfood.starwars_app.views.main.viewmodel.SearchViewModel
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
            val detailViewModel: CharacterDetailViewModel = koinViewModel()
            DetailScreen(
                character = detailViewModel.stateFlow.collectAsState().value,
                films = detailViewModel.filmsStateFlow.collectAsState().value
            )
        }
    }
}
