package com.snappfood.starwars_app.views.main.model

import com.snappfood.starwars_app.model.Character

fun Character.toUiModel(onClick: (character: Character) -> Unit): CharacterUiModel =
    CharacterUiModel(
        name = this.name,
        birth_year = this.birth_year,
        height = this.height,
        mass = this.mass,
        hair_color = this.hair_color,
        skin_color = this.skin_color,
        eye_color = this.eye_color,
        gender = this.gender,
        onClick = { onClick(this) }
    )