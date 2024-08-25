package com.snappfood.starwars_app.views.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.snappfood.starwars_app.model.Character

@Composable
fun DetailScreen(
    character: Character?,
) {

    character?.let {
        Column() {
            Text(text = character.name)
            Text(text = character.height)
            Text(text = character.mass)
            Text(text = character.hair_color)
        }
    }

}
