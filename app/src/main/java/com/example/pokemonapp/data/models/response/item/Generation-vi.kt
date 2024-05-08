package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class GenerationVI (

  @SerializedName("omegaruby-alphasapphire" ) var omegarubyAlphasapphire : OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
  @SerializedName("x-y"                     ) var xY                     : XY?                     = XY()

)