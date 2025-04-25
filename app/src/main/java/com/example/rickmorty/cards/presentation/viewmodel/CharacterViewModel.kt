package com.example.rickmorty.cards.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickmorty.cards.domain.entity.CharacterEntity
import com.example.rickmorty.cards.domain.repository.IRickRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterViewModel(
    private val repository: IRickRepository
) : ViewModel() {
    private val _characters = MutableStateFlow(emptyList<CharacterEntity>())
    val characters: StateFlow<List<CharacterEntity>> = _characters.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    suspend fun loadCharacters(forceRefresh: Boolean = false) {
        _isLoading.value = true
        _isError.value = false
        try {
            val charactersList = repository.getAllCharacters(forceRefresh)
            _characters.value = charactersList
        } catch (e: Exception) {
            _isError.value = true
        } finally {
            _isLoading.value = false
        }
    }
}
