package com.example.pokemonapp.data.local.converters

import androidx.room.TypeConverter
import com.example.pokemonapp.data.models.PokemonStat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromAbilitiesList(abilities: List<Pair<String, String>>?): String? {
        return gson.toJson(abilities)
    }

    @TypeConverter
    fun toAbilitiesList(abilitiesString: String?): List<Pair<String, String>>? {
        if (abilitiesString == null) {
            return null
        }
        val type = object : TypeToken<List<Pair<String, String>>>() {}.type
        return gson.fromJson(abilitiesString, type)
    }

    @TypeConverter
    fun fromStatsMap(stats: Map<String, PokemonStat>?): String? {
        return gson.toJson(stats)
    }

    @TypeConverter
    fun toStatsMap(statsString: String?): Map<String, PokemonStat>? {
        if (statsString == null) {
            return null
        }
        val type = object : TypeToken<Map<String, PokemonStat>>() {}.type
        return gson.fromJson(statsString, type)
    }
}