package com.snappfood.starwars_app.views.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.snappfood.starwars_app.data.CharacterRepository
import com.snappfood.starwars_app.data.SelectedItemDetailRepository
import com.snappfood.starwars_app.views.main.model.CharacterUiModel
import com.snappfood.starwars_app.views.main.model.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterViewModel(
    private val repository: CharacterRepository,
    private val selectedItemDetailRepository: SelectedItemDetailRepository,
) : ViewModel() {
    val charactersFlow: Flow<PagingData<CharacterUiModel>> =
        repository.getCharactersPagingFlow()
            .map {
                it.map {
                    it.toUiModel {
                        selectedItemDetailRepository.setValue(it)
                    }
                }
            }
            .cachedIn(viewModelScope)
}