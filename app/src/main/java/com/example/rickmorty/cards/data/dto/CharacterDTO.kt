package com.example.rickmorty.cards.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDTO,
    val location: LocationDTO,
    val episode: List<String>,
    val image: String,
    val url: String,
    val created: String
)