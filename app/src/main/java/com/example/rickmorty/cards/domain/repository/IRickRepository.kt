package com.example.rickmorty.cards.domain.repository

import com.example.rickmorty.cards.domain.entity.CharacterEntity

interface IRickRepository {
    suspend fun getAllCharacters(forceRefresh: Boolean = false) : List<CharacterEntity>
}