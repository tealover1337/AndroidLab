package com.example.rickmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.rickmorty.cards.data.repository.RickRepository
import com.example.rickmorty.cards.data.service.RickApiService
import com.example.rickmorty.cards.presentation.viewmodel.CharacterViewModel
import com.example.rickmorty.cards.presentation.viewmodel.CharacterViewModelFactory
import com.example.rickmorty.common.database.Database
import com.example.rickmorty.ui.theme.RickMortyTheme
import androidx.compose.runtime.rememberCoroutineScope
import com.example.rickmorty.cards.presentation.view.CharacterScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: CharacterViewModel by viewModels {
        val database = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            Database.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()

        val repository = RickRepository(
            apiService = RickApiService,
            dao = database.charactersDAO()
        )
        CharacterViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            RickMortyTheme {
                val characters by viewModel.characters.collectAsState()
                val isLoading by viewModel.isLoading.collectAsState()
                val isError by viewModel.isError.collectAsState()

                val coroutineScope = rememberCoroutineScope()

                LaunchedEffect(Unit) {
                    viewModel.loadCharacters(true)
                }

                CharacterScreen(
                    characters = characters,
                    isLoading = isLoading,
                    isError = isError,
                    onRefresh = {
                        coroutineScope.launch {
                            viewModel.loadCharacters()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
