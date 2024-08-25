package com.snappfood.starwars_app.domain

import com.snappfood.starwars_app.model.Character

data  class CharacterUiModel (
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String ,
    val onClick:()->Unit
)