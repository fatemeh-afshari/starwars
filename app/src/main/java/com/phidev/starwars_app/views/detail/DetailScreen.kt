package com.phidev.starwars_app.views.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.phidev.starwars_app.model.Character

@Composable
fun DetailScreen(
    name: String?
) {
    val response = Character(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )

    Column() {
        Text(text = response.name)
        Text(text = response.height)
        Text(text = response.mass)
        Text(text = response.hair_color)
    }
}
