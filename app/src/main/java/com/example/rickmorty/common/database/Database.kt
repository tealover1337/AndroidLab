package com.example.rickmorty.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rickmorty.cards.data.model.CharacterModel
import com.example.rickmorty.cards.data.service.CharactersDAO

@Database(
    entities = [CharacterModel::class],
    version = 3,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun charactersDAO(): CharactersDAO

    companion object {
        const val DATABASE_NAME = "characters.db"
    }
}
