package com.example.pokemonapp.features.pagination.data

interface Paginator<Key, Pokemon> {
    fun loadNextItems()
    fun reset()
}