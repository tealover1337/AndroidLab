package com.example.rickmorty.cards.data.service

import com.example.rickmorty.cards.data.dto.CharacterDTO
import com.example.rickmorty.cards.data.dto.CharacterListDTO
import com.example.rickmorty.common.api.NetworkModule
import io.ktor.client.call.body
import io.ktor.client.request.get

object RickApiService {
    private const val BASE_URL = "https://rickandmortyapi.com/api"

    suspend fun getAllCharacters(): List<CharacterDTO> {
        val characterList = mutableListOf<CharacterDTO>()
        var page = 1
        var hasNextPage = true
        val startTime = System.currentTimeMillis()

        try {
            while (hasNextPage) {
                val response = try {
                    NetworkModule.publicClient.get("$BASE_URL/character?page=$page")
                        .body<CharacterListDTO>()
                } catch (e: Exception) {
                    println("Error fetching page $page: ${e.message}")
                    return emptyList()
                }

                response.results.let { characters ->
                    characterList.addAll(characters)
                }

                hasNextPage = response.info.next != null
                page++
            }
        } finally {
            val totalTime = System.currentTimeMillis() - startTime
            println("got ${characterList.size} characters in $totalTime ms")
        }

        return characterList
    }

    suspend fun getCharacterById(id: Int) : CharacterDTO {
        return NetworkModule.publicClient.get("$BASE_URL/character/$id").body()
    }
}