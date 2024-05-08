package com.example.pokemonapp.data.models.response.item

import android.graphics.Color.blue
import com.google.gson.annotations.SerializedName


data class GenerationI (

  @SerializedName("red-blue" ) var redblue : RedBlue? = RedBlue(),
  @SerializedName("yellow"   ) var yellow   : Yellow?   = Yellow()

)