package com.example.pokemonapp.data.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
