package com.snappfood.starwars_app.data

import com.snappfood.starwars_app.model.Film
import com.snappfood.starwars_app.network.ApiService
import retrofit2.Response

class FilmRepository(private val api:ApiService) {


    suspend fun  getFilmDetail(id:Int):Response<Film>{
        return api.getFilmDetail(id)
    }
}