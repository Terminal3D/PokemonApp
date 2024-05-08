package com.example.pokemonapp.api

import com.example.pokemonapp.data.models.PokemonListItem
import javax.inject.Inject

private const val ICON_SRC_LINK =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"


class ApiRepositoryImpl @Inject constructor(
    private val api : ApiService
) : ApiRepository {
    override suspend fun getPokemonListItems(): List<PokemonListItem> {
        val responsesList = api.getPokemonList()
        val pokemonList = mutableListOf<PokemonListItem>()
        responsesList.results.forEach {
            println()
            val pokemonId = getIdFromURL(it.url)
            pokemonList.add(
                PokemonListItem(
                    id = pokemonId,
                    name = it.name ?: "",
                    iconSrc = getIconSrc(pokemonId)
                )
            )
        }
        return pokemonList
    }

    private fun getIconSrc(id: Int) = "$ICON_SRC_LINK$id.png"

    private fun getIdFromURL(url: String?) : Int {
        val splitted = url?.split("/")
        return splitted?.get(splitted.lastIndex.minus(1))?.toInt() ?: 1
    }

    override suspend fun getPokemon(id: Int): PokemonListItem {
        TODO("Not yet implemented")
    }
}