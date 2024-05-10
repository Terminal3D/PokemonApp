package com.example.pokemonapp.features.pokemoncard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PokemonAbilitiesColumn(
    abilities: List<Pair<String, String>>
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Box() {
            Text(text = "Abilities", style = MaterialTheme.typography.headlineLarge)
        }
        abilities.forEach { it ->
            PokemonAbility(name = it.first, description = it.second)
        }
    }
}


@Composable
fun PokemonAbility(
    name: String,
    description: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row {
            Text(text = description, style = MaterialTheme.typography.titleMedium)
        }
    }

}