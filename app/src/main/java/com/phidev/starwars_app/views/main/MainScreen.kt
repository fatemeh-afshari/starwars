package com.phidev.starwars_app.views.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController,
    characterViewModel: CharacterViewModel,
    searchViewModel: SearchViewModel,
) {
    val searchValue = searchViewModel.searchTextStateFlow.collectAsState()
    Column {
        Header()
        Searchbar(searchValue.value , searchViewModel::onTextChanged, searchViewModel::finishSearch)
        CharacterListScreen(viewModel = characterViewModel , searchViewModel = searchViewModel)
    }
}
