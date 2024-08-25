package com.snappfood.starwars_app.views.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.flatMap
import androidx.paging.map
import com.snappfood.starwars_app.data.CharacterRepository
import com.snappfood.starwars_app.data.SelectedItemDetailRepository
import com.snappfood.starwars_app.domain.CharacterUiModel
import com.snappfood.starwars_app.domain.toUiModel
import com.snappfood.starwars_app.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val selectedItemDetailRepository: SelectedItemDetailRepository,
) : ViewModel() {
    private val state: MutableStateFlow<Character?> = MutableStateFlow(null)
    val stateFlow = state.asStateFlow()
    init {
        viewModelScope.launch {
            selectedItemDetailRepository.getStateFlow().collectLatest {
                state.value = it
            }
        }
    }
}