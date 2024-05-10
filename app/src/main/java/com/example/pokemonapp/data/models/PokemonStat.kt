package com.example.pokemonapp.data.models

import androidx.annotation.DrawableRes

data class PokemonStat(
    val name : String,
    val value : Int,
    @DrawableRes val iconSrc : Int
)
