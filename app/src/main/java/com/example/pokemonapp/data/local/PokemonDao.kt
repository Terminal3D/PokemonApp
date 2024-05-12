package com.example.pokemonapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.pokemonapp.data.models.Pokemon

@Dao
interface PokemonDao {

    @Upsert
    suspend fun upsertAll(pokemons : List<PokemonListEntity>)

    @Query("SELECT * FROM pokemonlistentity")
    fun pagingSource() : PagingSource<Int, PokemonListEntity>

    @Query("DELETE FROM pokemonlistentity")
    suspend fun clearAll()

    @Upsert
    suspend fun upsertPokemon(pokemon : PokemonEntity)

    @Delete
    suspend fun deletePokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemonentity WHERE id = :pokemonId")
    suspend fun getPokemon(pokemonId: Int) : Pokemon?
}