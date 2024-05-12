package com.example.pokemonapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemonapp.data.models.PokemonStat

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id : Int,
    val name : String = "",
    val iconSrc : String = "",
    val abilities : List<Pair<String, String>>? = null,
    val stats : Map<String, PokemonStat>? = null
)
