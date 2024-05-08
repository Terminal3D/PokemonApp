package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class Stats (

  @SerializedName("base_stat" ) var baseStat : Int?  = null,
  @SerializedName("effort"    ) var effort   : Int?  = null,
  @SerializedName("stat"      ) var stat     : Stat? = Stat()

)