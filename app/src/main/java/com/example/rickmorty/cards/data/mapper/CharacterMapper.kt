package com.example.rickmorty.cards.data.mapper

import com.example.rickmorty.cards.data.dto.CharacterDTO
import com.example.rickmorty.cards.domain.entity.CharacterEntity
import com.example.rickmorty.cards.data.model.CharacterModel
import androidx.core.net.toUri

abstract class CharacterMapper {
    companion object {
        fun mapDTOToEntity(dto: CharacterDTO): CharacterEntity =
            CharacterEntity(
                id = dto.id,
                name = dto.name,
                status = dto.status,
                species = dto.species,
                image = dto.image.toUri()
            )

        fun mapModelToEntity(model: CharacterModel): CharacterEntity =
            CharacterEntity(
                id = model.id,
                name = model.name,
                status = model.status,
                species = model.species,
                image = model.image.toUri()
            )

        fun mapDTOToModel(dto: CharacterDTO): CharacterModel =
            CharacterModel(
                id = dto.id,
                name = dto.name,
                image = dto.image,
                status = dto.status,
                species = dto.species
            )
    }
}