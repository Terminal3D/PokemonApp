package com.example.pokemonapp.data.models.response.list

import com.google.gson.annotations.SerializedName

data class PokemonListResponseItem(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
