package com.example.pokemonapp.data.models

data class Pokemon(
    val name : String,
    val iconSrc : String,
    val abilities : List<Pair<String, String>>

)
