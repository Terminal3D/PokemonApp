package com.example.pokemonapp.api

import com.example.pokemonapp.data.models.PokemonListItem
import com.example.pokemonapp.data.models.response.ability.AbilityItemResponse
import com.example.pokemonapp.data.models.response.item.PokemonItemResponse
import com.example.pokemonapp.data.models.response.list.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): PokemonListResponse

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonById(@Path("id") pokemonID: Int): PokemonItemResponse

    @GET("api/v2/ability/{id}")
    suspend fun getAbilityById(@Path("id") abilityID: Int): AbilityItemResponse
}