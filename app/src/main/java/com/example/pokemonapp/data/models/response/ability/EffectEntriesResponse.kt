package com.example.pokemonapp.data.models.response.ability

import com.example.pokemonapp.data.models.response.common.LanguageResponse
import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Language

data class EffectEntriesResponse (

    @SerializedName("effect"       ) var effect      : String?   = null,
    @SerializedName("language"     ) var language    : LanguageResponse? = LanguageResponse(),
    @SerializedName("short_effect" ) var shortEffect : String?   = null

)