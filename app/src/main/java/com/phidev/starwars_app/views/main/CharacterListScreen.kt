package com.phidev.starwars_app.views.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.phidev.starwars_app.model.Character

@Composable
fun CharacterListScreen(viewModel: CharacterViewModel, searchViewModel: SearchViewModel) {
    val characters = viewModel.charactersFlow.collectAsLazyPagingItems()
    val searchResult = searchViewModel.stateFlow.collectAsState()
    when (searchResult.value) {
        is LoadableData.Failed -> ErrorItem(
            message = (searchResult.value as LoadableData.Failed).error,
            searchViewModel::search
        )
        is LoadableData.Loaded -> SearchList(searchResult.value)
        LoadableData.Loading -> LoadingItem()
        LoadableData.NotLoaded -> List(characters)
    }


}

@Composable
private fun SearchList(searchResult: LoadableData) {
    if(searchResult is LoadableData.Loaded ) {
        LazyColumn {
            items(searchResult.result.results) { character ->
                CharacterItem(character)

            }
        }
    }
}

@Composable
private fun List(characters: LazyPagingItems<Character>) {
    LazyColumn {
        items(characters) { character ->
            character?.let {
                CharacterItem(it)
            }
        }

        characters.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "Unknown error",
                            onRetryClick = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "Unknown error",
                            onRetryClick = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${character.name}")
            Text(text = "Height: ${character.height}")
            Text(text = "Mass: ${character.mass}")
            // Add more character details as needed
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorItem(message: String, onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetryClick) {
            Text(text = "Retry")
        }
    }
}