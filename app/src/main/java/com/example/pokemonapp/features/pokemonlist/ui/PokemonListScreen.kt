package com.example.pokemonapp.features.pokemonlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.example.pokemonapp.data.models.PokemonListItem
import com.example.pokemonapp.features.pokemonlist.ui.components.PokemonListScreenTopBar


@Composable
fun PokemonListScreen(
    pokemonList: LazyPagingItems<PokemonListItem>,
    onItemChoose: (Int) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = pokemonList.loadState) {
        if (pokemonList.loadState.refresh is LoadState.Error) {
            snackbarHostState.showSnackbar("Error: " + (pokemonList.loadState.refresh as LoadState.Error).error.message)
        }
    }
    Scaffold(
        topBar = { PokemonListScreenTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        if (pokemonList.loadState.refresh is LoadState.Loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(paddingValues)
            ) {
                itemsIndexed(pokemonList) { index, item ->
                    if (item != null) {
                        PokemonCard(item) { onItemChoose(item.id) }
                        if (index != pokemonList.itemCount - 1) {
                            Divider()
                        }
                    }
                }
                item {
                    if (pokemonList.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
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