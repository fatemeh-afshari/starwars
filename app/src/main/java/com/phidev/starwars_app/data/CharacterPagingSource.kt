package com.phidev.starwars_app.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.phidev.starwars_app.model.Character
import com.phidev.starwars_app.network.ApiService

class CharacterPagingSource(
    private val api: ApiService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = api.getCharacterList(page)
            
            if (response.isSuccessful) {
                val characterResponse = response.body()!!
                val nextKey = if (characterResponse.next != null) page + 1 else null
                val prevKey = if (page > 1) page - 1 else null
                
                LoadResult.Page(
                    data = characterResponse.results,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception("API call failed"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}