package com.snappfood.starwars_app.data

import com.snappfood.starwars_app.model.Character
import kotlinx.coroutines.flow.MutableStateFlow

class SelectedItemDetailRepository {

    private val characterDetailStateFlow  = MutableStateFlow<Character?>(null)


    fun setValue(value:Character) {
        characterDetailStateFlow.value = value
    }

    fun getStateFlow() = characterDetailStateFlow
}