package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class Ability (

  @SerializedName("name" ) var name : String? = null,
  @SerializedName("url"  ) var url  : String? = null

)