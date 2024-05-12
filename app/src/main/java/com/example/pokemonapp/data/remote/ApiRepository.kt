package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.models.Pokemon
import com.example.pokemonapp.data.models.PokemonListItem

interface ApiRepository {

    suspend fun getPokemonListItems(page: Int, pageSize : Int): List<PokemonListItem>

    suspend fun getPokemon(id: Int): Pokemon

    suspend fun getAbility(id: Int): String

}