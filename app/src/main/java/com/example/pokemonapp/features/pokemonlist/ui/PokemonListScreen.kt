package com.example.pokemonapp.features.pokemonlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pokemonapp.data.models.PokemonListItem
import com.example.pokemonapp.features.pokemonlist.viewmodel.PokemonListUIState


@Composable
fun PokemonListScreen(
    uiState: PokemonListUIState,
    onItemChoose: (Int) -> Unit,
) {
    when (uiState) {
        is PokemonListUIState.Error -> Text(uiState.error)
        PokemonListUIState.Loading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

        is PokemonListUIState.Success -> PokemonListScreenContent(
            pokemonList = uiState.pokemonList,
            onItemChoose = onItemChoose
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreenContent(
    pokemonList: List<PokemonListItem>,
    onItemChoose: (Int) -> Unit
) {
    LazyColumn(

    ) {
        itemsIndexed(pokemonList) { index, item ->
            ElevatedCard(onClick = { onItemChoose(item.id) }) {
                Text(item.id.toString())
                Text(item.name)
                Text(item.iconSrc)
            }
        }
    }
}
