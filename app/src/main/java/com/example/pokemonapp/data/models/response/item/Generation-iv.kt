package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class GenerationIV(

    @SerializedName("diamond-pearl") var diamondPearl: DiamondPearl? = DiamondPearl(),
    @SerializedName("heartgold-soulsilver") var heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
    @SerializedName("platinum") var platinum: Platinum? = Platinum()

)