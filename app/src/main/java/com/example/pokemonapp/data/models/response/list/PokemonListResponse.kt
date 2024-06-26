package com.example.pokemonapp.data.models.response.list

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: List<PokemonListResponseItem> = listOf()
)