package com.snappfood.starwars_app.views.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snappfood.starwars_app.data.SearchRepository
import com.snappfood.starwars_app.data.SelectedItemDetailRepository
import com.snappfood.starwars_app.model.CharacterResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository,
    private val selectedItemDetailRepository: SelectedItemDetailRepository,
) : ViewModel() {
    private val state: MutableStateFlow<LoadableData> = MutableStateFlow(LoadableData.NotLoaded)
    val stateFlow = state.asStateFlow()


    private val searchTextState: MutableStateFlow<String> = MutableStateFlow("")
    val searchTextStateFlow = searchTextState.asStateFlow()
    fun onTextChanged(value: String) {
        searchTextState.value = value
        if (value.length >= 3) {
            search()
        } else if (value.isBlank()) {
            finishSearch()
        }

    }

    fun search() {
        viewModelScope.launch {
            state.value = LoadableData.Loading
            runCatching {

                repository.getSearchResult(searchTextState.value)
            }.onFailure {
                state.value = LoadableData.Failed("something went wrong")
            }.onSuccess {
                it.body()?.let {

                    state.value = LoadableData.Loaded(it)
                } ?: kotlin.run {
                    state.value = LoadableData.Failed("Not found")
                }
            }

        }
    }

    fun finishSearch() {
        searchTextState.value = ""
        state.value = LoadableData.NotLoaded
    }
}


sealed class LoadableData {
    object NotLoaded : LoadableData()
    object Loading : LoadableData()

    class Loaded(val result: CharacterResult) : LoadableData()

    class Failed(val error: String) : LoadableData()

}