package com.example.pokemonapp.features.navigation.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapp.core.theme.PokemonAppTheme
import com.example.pokemonapp.features.navigation.NavGraph

@Composable
fun PokemonApp() {
    PokemonAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavGraph(
                navHostController = rememberNavController(),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
