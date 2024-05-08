package com.example.pokemonapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokemonapp.features.pokemonlist.ui.PokemonListScreen
import com.example.pokemonapp.features.pokemonlist.viewmodel.PokemonListViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = Sections.ListScreen.route,
        modifier = modifier
    ) {
        composable(Sections.ListScreen.route) {
            val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()
            val state = pokemonListViewModel.state.collectAsState()
            PokemonListScreen(
                uiState = state.value,
                onItemChoose = {}
            )
        }

    }
}