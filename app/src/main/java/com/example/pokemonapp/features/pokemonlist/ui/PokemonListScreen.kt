package com.example.pokemonapp.features.pokemonlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonListItem
import com.example.pokemonapp.features.pokemonlist.ui.components.PokemonListScreenTopBar
import com.example.pokemonapp.features.pokemonlist.viewmodel.PokemonListUIState
import java.util.Locale


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

@Composable
fun PokemonListScreenContent(
    pokemonList: List<PokemonListItem>,
    onItemChoose: (Int) -> Unit
) {
    Scaffold(
        topBar = { PokemonListScreenTopBar() }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            itemsIndexed(pokemonList) { index, item ->
                PokemonCard(item) { onItemChoose(item.id) }
                if (index != pokemonList.lastIndex) {
                    Divider()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(
    item: PokemonListItem,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        shape = RectangleShape,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = item.iconSrc,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                item.name,
                style = MaterialTheme.typography.titleLarge
            )
        }

    }
}