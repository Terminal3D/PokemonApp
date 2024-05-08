package com.example.pokemonapp.features.pokemoncard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pokemonapp.api.ApiRepository
import com.example.pokemonapp.data.models.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed interface PokemonScreenUiState {

    object Loading : PokemonScreenUiState

    data class Error(val error: String) : PokemonScreenUiState

    data class Success(val pokemonData : Pokemon) : PokemonScreenUiState

}


@HiltViewModel
class PokemonScreenViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

}