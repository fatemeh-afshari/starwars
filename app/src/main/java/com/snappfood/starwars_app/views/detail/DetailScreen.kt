package com.snappfood.starwars_app.views.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snappfood.starwars_app.model.Character
import com.snappfood.starwars_app.model.Film

@Composable
fun DetailScreen(
    character: Character?,
    films:List<Film?>
) {

    character?.let {
        Column {
            Text(text = "name: "+character.name)
            Text(text = "height: "+character.height)
            Text(text = "mass: "+character.mass)
            Text(text = "hair color: "+character.hair_color)
            Text(text = "eye color: "+character.eye_color)
            Text(text = "gender: "+character.gender)
            Text(text = "skin color: "+character.skin_color)
            Text(text = "birth year: "+character.birth_year)
            Text(text = "url: "+character.url)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Films:")
            LazyColumn {
                items(films){
                  Column {
                     Text("title: "+ it?.title)
                     Text("opening crawl: "+ it?.opening_crawl)
                  }
                }


            }
        }
    }

}
