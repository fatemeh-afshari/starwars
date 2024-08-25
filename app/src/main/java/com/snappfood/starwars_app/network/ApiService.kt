package com.snappfood.starwars_app.network

import com.snappfood.starwars_app.model.CharacterResult
import com.snappfood.starwars_app.model.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("people")
    suspend fun getCharacterList(@Query("page")page:Int): Response<CharacterResult>
    @GET("people")
    suspend fun searchCharacterByName(@Query("search")search:String): Response<CharacterResult>


    @GET("films/{filmId}")
    suspend fun getFilmDetail(@Path("filmId") filmId:Int): Response<Film>

}