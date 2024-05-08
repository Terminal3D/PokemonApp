package com.example.pokemonapp.features.pokemoncard.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokemonapp.data.models.Pokemon
import com.example.pokemonapp.features.pokemoncard.ui.components.PokemonScreenTopBar
import com.example.pokemonapp.features.pokemoncard.viewmodel.PokemonScreenUiState

@Composable
fun PokemonScreen(
    uiState: PokemonScreenUiState,
    onPressCallback: () -> Unit
) {
    when (uiState) {
        is PokemonScreenUiState.Error -> Text(uiState.error)
        PokemonScreenUiState.Loading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

        is PokemonScreenUiState.Success -> PokemonScreenContent(
            pokemon = uiState.pokemonData,
            onPressCallback = onPressCallback
        )
    }
}


@Composable
fun PokemonScreenContent(
    pokemon: Pokemon,
    onPressCallback: () -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            PokemonScreenTopBar(
                name = pokemon.name,
                onBackPressedCallback = onPressCallback
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .horizontalScroll(scrollState)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    LazyColumn {
                        itemsIndexed(pokemon.abilities) { index, item ->
                            Column {
                                Text(text = item.first)
                                Text(text = item.second)
                            }
                        }
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun PokemonScreenPreview() {
    //PokemonScreenContent() {
//
    //  }
}