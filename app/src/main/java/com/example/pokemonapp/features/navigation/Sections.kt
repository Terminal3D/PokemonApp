package com.example.pokemonapp.features.navigation

import okhttp3.Route


sealed class Sections(val route : String) {
    object ListScreen : Sections(route = "list_screen")
}