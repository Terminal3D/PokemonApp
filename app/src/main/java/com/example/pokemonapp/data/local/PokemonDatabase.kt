package com.example.pokemonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemonapp.data.local.converters.Converters
import com.example.pokemonapp.data.models.Pokemon

@Database(
    entities = [PokemonListEntity::class, PokemonEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val dao : PokemonDao
}