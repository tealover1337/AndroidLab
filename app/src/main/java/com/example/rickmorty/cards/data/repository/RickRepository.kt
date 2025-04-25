package com.example.rickmorty.cards.data.repository

import androidx.room.util.foreignKeyCheck
import com.example.rickmorty.cards.data.dto.CharacterListDTO
import com.example.rickmorty.cards.data.mapper.CharacterMapper
import com.example.rickmorty.cards.data.service.CharactersDAO
import com.example.rickmorty.cards.data.service.RickApiService
import com.example.rickmorty.cards.domain.entity.CharacterEntity
import com.example.rickmorty.cards.domain.repository.IRickRepository

class RickRepository(
    private val apiService: RickApiService,
    private val dao: CharactersDAO,
) : IRickRepository {
    override suspend fun getAllCharacters(forceRefresh: Boolean) : List<CharacterEntity> {
        return try {
            println("getting all chars in repository")
            val localData = dao.getAllCharacters()
            if (localData.isEmpty() || forceRefresh) {
                println("getting data in api")
                val remoteData = apiService.getAllCharacters()
                dao.insertAll(remoteData.map { CharacterMapper.mapDTOToModel(it) })
            }
            dao.getAllCharacters().map { CharacterMapper.mapModelToEntity(it) }
        } catch (e: Exception) {
            println("error: ${e.message} ${e.cause}")
            dao.getAllCharacters().map { CharacterMapper.mapModelToEntity(it) }
        }
    }
}