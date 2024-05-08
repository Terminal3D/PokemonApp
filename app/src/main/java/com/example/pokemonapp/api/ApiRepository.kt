package com.example.pokemonapp.api

import com.example.pokemonapp.data.models.PokemonListItem

interface ApiRepository {

    suspend fun getPokemonListItems(): List<PokemonListItem>

    suspend fun getPokemon(id: Int): PokemonListItem

}