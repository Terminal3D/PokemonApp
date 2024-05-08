package com.example.pokemonapp.features.pokemonlist.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.pokemonapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreenTopBar() {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(R.string.pokemon_list),
            style = MaterialTheme.typography.displaySmall
        )
    })
}