package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class GenerationV (

  @SerializedName("black-white" ) var blackWhite : BlackWhite? = BlackWhite()

)