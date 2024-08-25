package com.snappfood.starwars_app.views.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snappfood.starwars_app.data.FilmRepository
import com.snappfood.starwars_app.data.SelectedItemDetailRepository
import com.snappfood.starwars_app.model.Character
import com.snappfood.starwars_app.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val selectedItemDetailRepository: SelectedItemDetailRepository,
    private val filmRepository: FilmRepository,
) : ViewModel() {
    private val state: MutableStateFlow<Character?> = MutableStateFlow(null)
    val stateFlow = state.asStateFlow()

    private val filmsState: MutableStateFlow<List<Film?>> = MutableStateFlow(emptyList())
    val filmsStateFlow = filmsState.asStateFlow()

    init {
        viewModelScope.launch {
            selectedItemDetailRepository.getStateFlow().collectLatest {
                state.value = it
                state.value?.let {
                    getFilmsDetail(it.films)
                }
            }
        }
    }


    private fun getFilmsDetail(films: List<String>) {
        viewModelScope.launch {
            filmsState.value = films.map {
                runCatching {
                    filmRepository.getFilmDetail(it.filter { it.isDigit() }.toInt())
                }.getOrNull()?.body()
            }
        }

    }
}