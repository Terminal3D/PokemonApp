package com.example.pokemonapp.features.navigation

import okhttp3.Route


sealed interface Sections {
    object ListScreen : Sections {
        const val ROUTE = "list_screen"
    }

    sealed class ItemScreen() : Sections {
        companion object {
            const val ROUTE = "item_screen/{id}"
            fun getRoute(id: Int) = "item_screen/$id"
        }
    }
}