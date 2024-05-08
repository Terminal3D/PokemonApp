package com.example.pokemonapp.api

import com.example.pokemonapp.data.models.PokemonListItem
import com.example.pokemonapp.data.models.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v2/pokemon")
    suspend fun getPokemonList() : PokemonListResponse

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonById(@Path("id") pokemonID : Int) : PokemonListItem

}