package com.phidev.starwars_app.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.phidev.starwars_app.model.Character
import com.phidev.starwars_app.network.ApiService
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val api: ApiService) {

    fun getCharactersPagingFlow(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(api) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}