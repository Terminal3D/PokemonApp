package com.example.pokemonapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokemonapp.features.pokemoncard.ui.PokemonScreen
import com.example.pokemonapp.features.pokemoncard.viewmodel.PokemonScreenViewModel
import com.example.pokemonapp.features.pokemonlist.ui.PokemonListScreen
import com.example.pokemonapp.features.pokemonlist.viewmodel.PokemonListViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = Sections.ListScreen.ROUTE,
        modifier = modifier
    ) {
        composable(Sections.ListScreen.ROUTE) {
            val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()
            val pokemons = pokemonListViewModel.pokemonPagingFlow.collectAsLazyPagingItems()
            PokemonListScreen(
                pokemonList = pokemons,
                onItemChoose = { id -> navHostController.navigate(Sections.ItemScreen.getRoute(id))}
            )
        }

        composable(Sections.ItemScreen.ROUTE) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("id")?.let { id ->
                val pokemonScreenViewModel = hiltViewModel<PokemonScreenViewModel>()
                remember { pokemonScreenViewModel.getPokemon(id.toInt()) }
                val state = pokemonScreenViewModel.state.collectAsState()
                PokemonScreen(
                    uiState = state.value,
                    onPressCallback = { navHostController.popBackStack() }
                )
            }
        }

    }
}