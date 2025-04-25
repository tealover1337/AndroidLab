package com.example.rickmorty.cards.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListInfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)