package com.example.rickmorty.cards.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val name: String,
    val url: String,
)
