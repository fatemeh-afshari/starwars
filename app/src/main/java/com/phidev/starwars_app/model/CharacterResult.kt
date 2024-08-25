package com.phidev.starwars_app.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResult(
    val count: Int ,
    val next: String? ,
    val previous: String? ,
    val results: List<Character>
)
