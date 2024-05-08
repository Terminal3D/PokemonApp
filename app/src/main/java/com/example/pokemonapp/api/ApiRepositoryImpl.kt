package com.example.pokemonapp.api

import com.example.pokemonapp.data.models.Pokemon
import com.example.pokemonapp.data.models.PokemonListItem
import com.example.pokemonapp.data.models.response.item.Abilities
import java.util.Locale
import javax.inject.Inject

private const val ICON_SRC_LINK =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"


class ApiRepositoryImpl @Inject constructor(
    private val api: ApiService
) : ApiRepository {
    override suspend fun getPokemonListItems(): List<PokemonListItem> {
        val responsesList = api.getPokemonList()
        val pokemonList = mutableListOf<PokemonListItem>()
        responsesList.results.forEach { item ->
            println()
            val pokemonId = getIdFromURL(item.url)
            pokemonList.add(
                PokemonListItem(
                    id = pokemonId,
                    name = capitalizeItem(item.name),
                    iconSrc = getIconSrc(pokemonId)
                )

            )
        }
        return pokemonList
    }

    private fun capitalizeItem(item: String?) =
        item?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            ?: ""

    private fun getIconSrc(id: Int) = "$ICON_SRC_LINK$id.png"

    private fun getIdFromURL(url: String?): Int {
        val splitted = url?.split("/")
        return splitted?.get(splitted.lastIndex.minus(1))?.toInt() ?: 1
    }

    override suspend fun getPokemon(id: Int): Pokemon {
        val response = api.getPokemonById(id)

        val name = capitalizeItem(response.name)
        val abilities = parseAbilities(response.abilities)

        return Pokemon(name = name, abilities = abilities, iconSrc = getIconSrc(id))
    }

    suspend fun parseAbilities(abilitiesResponse: List<Abilities?>): List<Pair<String, String>> {
        val abilities = mutableListOf<Pair<String, String>>()

        abilitiesResponse.forEach { it ->
            val abilityID = getIdFromURL(it?.ability?.url)
            val abilityShortDescription = getAbility(abilityID)
            val abilityItem = Pair(it?.ability?.name ?: "", abilityShortDescription)
            abilities.add(abilityItem)
        }

        return abilities
    }

    override suspend fun getAbility(id: Int): String {
        val response = api.getAbilityById(id)
        var ability = ""
        response.effectEntries.forEach { it ->
            if (it.language?.name == "en") {
                ability = it.shortEffect ?: ""
            }
        }
        return ability
    }
}