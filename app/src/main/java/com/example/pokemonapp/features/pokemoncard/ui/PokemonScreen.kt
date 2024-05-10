package com.example.pokemonapp.features.pokemoncard.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonapp.data.models.Pokemon
import com.example.pokemonapp.features.pokemoncard.ui.components.PokemonAbilitiesColumn
import com.example.pokemonapp.features.pokemoncard.ui.components.PokemonScreenTopBar
import com.example.pokemonapp.features.pokemoncard.ui.components.PokemonStatsColumn
import com.example.pokemonapp.features.pokemoncard.viewmodel.PokemonScreenUiState
import org.jetbrains.annotations.Async

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
                name = "Characteristics",
                onBackPressedCallback = onPressCallback
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = pokemon.iconSrc,
                    contentDescription = null,
                    modifier = Modifier.size(256.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp)
            ) {
                Text(text = pokemon.name, style = MaterialTheme.typography.displayMedium)
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            if (pokemon.stats != null) {
                PokemonStatsColumn(stats = pokemon.stats)
                Divider(modifier = Modifier.padding(vertical = 24.dp))
            }
            if (pokemon.abilities != null) {
                PokemonAbilitiesColumn(abilities = pokemon.abilities)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
fun PokemonScreenPreview() {
    PokemonScreenContent(
        pokemon = Pokemon(),

        ) {

    }
}