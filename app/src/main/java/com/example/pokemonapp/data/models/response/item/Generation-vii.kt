package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class GenerationVII(

    @SerializedName("icons") var icons: Icons? = Icons(),
    @SerializedName("ultra-sun-ultra-moon") var ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()

)