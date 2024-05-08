package com.example.pokemonapp.features.pokemonlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.api.ApiRepository
import com.example.pokemonapp.data.models.PokemonListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PokemonListUIState {

    data class Success(val pokemonList: List<PokemonListItem>) : PokemonListUIState

    object Loading : PokemonListUIState

    data class Error(val error : String) : PokemonListUIState
}

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _state = MutableStateFlow<PokemonListUIState>(PokemonListUIState.Loading)
    val state = _state.asStateFlow()

    init {
        getPokemons()
    }

    fun getPokemons() = viewModelScope.launch {
        _state.update { PokemonListUIState.Loading }
        try {
            val pokemons = apiRepository.getPokemonListItems()
            _state.update { PokemonListUIState.Success(pokemons) }
        } catch (e: Exception) {
            _state.update { PokemonListUIState.Error(e.toString()) }
        }
    }

}