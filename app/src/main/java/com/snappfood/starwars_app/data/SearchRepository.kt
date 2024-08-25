package com.snappfood.starwars_app.data

import com.snappfood.starwars_app.model.CharacterResult
import com.snappfood.starwars_app.network.ApiService
import retrofit2.Response

class SearchRepository(private val api: ApiService) {

    suspend fun getSearchResult(value: String): Response<CharacterResult> {
        return api.searchCharacterByName(value)
    }
}