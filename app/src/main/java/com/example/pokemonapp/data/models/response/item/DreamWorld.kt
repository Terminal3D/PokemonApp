package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class DreamWorld (

  @SerializedName("front_default" ) var frontDefault : String? = null,
  @SerializedName("front_female"  ) var frontFemale  : String? = null

)