package com.example.rickmorty.cards.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDTO(
    val info: CharacterListInfoDTO,
    val results: List<CharacterDTO>
)