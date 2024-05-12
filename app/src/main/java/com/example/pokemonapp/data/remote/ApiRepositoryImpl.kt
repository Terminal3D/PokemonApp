package com.example.pokemonapp.data.remote

import com.example.pokemonapp.R
import com.example.pokemonapp.data.remote.ApiConsts.ATTACK_KEY
import com.example.pokemonapp.data.remote.ApiConsts.ATTACK_RESPONSE
import com.example.pokemonapp.data.remote.ApiConsts.DEFENSE_KEY
import com.example.pokemonapp.data.remote.ApiConsts.DEFENSE_RESPONSE
import com.example.pokemonapp.data.remote.ApiConsts.HP_KEY
import com.example.pokemonapp.data.remote.ApiConsts.HP_RESPONSE
import com.example.pokemonapp.data.remote.ApiConsts.ICON_SRC_LINK
import com.example.pokemonapp.data.remote.ApiConsts.SPECIAL_ATTACK_KEY
import com.example.pokemonapp.data.remote.ApiConsts.SPECIAL_ATTACK_RESPONSE
import com.example.pokemonapp.data.remote.ApiConsts.SPECIAL_DEFENSE_KEY
import com.example.pokemonapp.data.remote.ApiConsts.SPECIAL_DEFENSE_RESPONSE
import com.example.pokemonapp.data.remote.ApiConsts.SPEED_KEY
import com.example.pokemonapp.data.remote.ApiConsts.SPEED_RESPONSE
import com.example.pokemonapp.data.models.Pokemon
import com.example.pokemonapp.data.models.PokemonListItem
import com.example.pokemonapp.data.models.PokemonStat
import com.example.pokemonapp.data.models.response.item.Abilities
import com.example.pokemonapp.data.models.response.item.Stats
import java.util.Locale
import javax.inject.Inject


object ApiConsts {
    const val ICON_SRC_LINK =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

    const val HP_RESPONSE = "hp"
    const val ATTACK_RESPONSE = "attack"
    const val DEFENSE_RESPONSE = "defense"
    const val SPECIAL_ATTACK_RESPONSE = "special-attack"
    const val SPECIAL_DEFENSE_RESPONSE = "special-defense"
    const val SPEED_RESPONSE = "speed"

    const val HP_KEY = "HP"
    const val ATTACK_KEY = "Attack"
    const val DEFENSE_KEY = "Defense"
    const val SPECIAL_ATTACK_KEY = "Sp. Attack"
    const val SPECIAL_DEFENSE_KEY = "Sp. Defense"
    const val SPEED_KEY = "Speed"

}

class ApiRepositoryImpl @Inject constructor(
    private val api: ApiService
) : ApiRepository {
    override suspend fun getPokemonListItems(page : Int, pageSize: Int): List<PokemonListItem> {
        val responsesList = api.getPokemonList(limit = pageSize, offset = page * pageSize)
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

        val abilities = if (response.abilities != null) {
            parseAbilities(response.abilities!!)
        } else {
            null
        }
        val stats = if (response.stats != null) {
            parseStats(response.stats!!)
        } else {
            null
        }

        return Pokemon(
            id = response.id!!,
            name = name,
            abilities = abilities,
            iconSrc = getIconSrc(id),
            stats = stats
        )
    }

    suspend fun parseAbilities(abilitiesResponse: List<Abilities>): List<Pair<String, String>> {
        val abilities = mutableListOf<Pair<String, String>>()

        abilitiesResponse.forEach { it ->
            val abilityID = getIdFromURL(it.ability?.url)
            val abilityShortDescription = getAbility(abilityID)
            val abilityItem = Pair(
                capitalizeItem(it.ability?.name) ?: "",
                capitalizeItem(abilityShortDescription)
            )
            abilities.add(abilityItem)
        }

        return abilities
    }

    fun parseStats(statsResponse: List<Stats>): Map<String, PokemonStat> {
        val stats = mutableMapOf<String, PokemonStat>()

        statsResponse.forEach {
            var key = ""
            var iconSrc = 0
            when (it.stat?.name) {
                HP_RESPONSE -> {
                    key = HP_KEY
                    iconSrc = R.drawable.ic_hp
                }
                ATTACK_RESPONSE -> {
                    key = ATTACK_KEY
                    iconSrc = R.drawable.ic_attack
                }
                DEFENSE_RESPONSE -> {
                    key = DEFENSE_KEY
                    iconSrc = R.drawable.ic_defense
                }
                SPECIAL_ATTACK_RESPONSE -> {
                    key = SPECIAL_ATTACK_KEY
                    iconSrc = R.drawable.ic_attack_sp
                }
                SPECIAL_DEFENSE_RESPONSE -> {
                    key = SPECIAL_DEFENSE_KEY
                    iconSrc = R.drawable.ic_defense_sp
                }
                SPEED_RESPONSE -> {
                    key = SPEED_KEY
                    iconSrc = R.drawable.ic_speed
                }
            }
            stats[key] = PokemonStat(
                name = key,
                value = it.baseStat!!,
                iconSrc = iconSrc
            )
        }
        return stats
    }

    override suspend fun getAbility(id: Int): String {
        val response = api.getAbilityById(id)
        var ability = ""
        response.effectEntries.forEach { it ->
            if (it.language?.name == "en") {
                ability = capitalizeItem(it.shortEffect)
            }
        }
        return ability
    }
}