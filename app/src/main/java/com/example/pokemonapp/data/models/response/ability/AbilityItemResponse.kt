package com.example.pokemonapp.data.models.response.ability

import com.google.gson.annotations.SerializedName

data class AbilityItemResponse(
    @SerializedName("effect_entries") var effectEntries: ArrayList<EffectEntriesResponse> = arrayListOf(),
)