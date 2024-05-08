package com.example.pokemonapp.features.pokemoncard.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.api.ApiRepository
import com.example.pokemonapp.data.models.Pokemon
import com.example.pokemonapp.features.pokemonlist.viewmodel.PokemonListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private val _state = MutableStateFlow<PokemonScreenUiState>(PokemonScreenUiState.Loading)
    val state = _state.asStateFlow()

    fun getPokemon(id : Int) = viewModelScope.launch {
        _state.update { PokemonScreenUiState.Loading }
        try {
            val pokemon = apiRepository.getPokemon(id)
            _state.update { PokemonScreenUiState.Success(pokemon) }
        } catch (e: Exception) {
            _state.update { PokemonScreenUiState.Error(e.toString()) }
        }
    }

}