package com.example.pokemonapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonListEntity(
    @PrimaryKey
    val id : Int,
    val name : String,
    val iconSrc : String
)
