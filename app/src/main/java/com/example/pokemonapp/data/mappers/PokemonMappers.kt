package com.example.pokemonapp.data.mappers

import com.example.pokemonapp.data.local.PokemonEntity
import com.example.pokemonapp.data.local.PokemonListEntity
import com.example.pokemonapp.data.models.Pokemon
import com.example.pokemonapp.data.models.PokemonListItem

fun PokemonListEntity.toPokemonListItem() : PokemonListItem {
    return PokemonListItem(
        id = id,
        name = name,
        iconSrc = iconSrc
    )
}

fun PokemonListItem.toEntity() : PokemonListEntity {
    return PokemonListEntity(
        id = id,
        name = name,
        iconSrc = iconSrc
    )
}

fun Pokemon.toEntity() : PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        iconSrc = iconSrc,
        abilities = abilities,
        stats = stats
    )
}

fun PokemonEntity.toPokemon() : Pokemon {
    return Pokemon(
        id = id,
        name = name,
        iconSrc = iconSrc,
        abilities = abilities,
        stats = stats
    )
}