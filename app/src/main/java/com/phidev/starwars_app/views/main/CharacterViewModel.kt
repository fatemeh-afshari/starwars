package com.phidev.starwars_app.views.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.phidev.starwars_app.data.CharacterRepository
import com.phidev.starwars_app.data.SelectedItemDetailRepository
import com.phidev.starwars_app.model.Character
import kotlinx.coroutines.flow.Flow

class CharacterViewModel(
    private val repository: CharacterRepository,
    private val selectedItemDetailRepository: SelectedItemDetailRepository,
) : ViewModel() {

    val charactersFlow: Flow<PagingData<Character>> =
        repository.getCharactersPagingFlow().cachedIn(viewModelScope)
}