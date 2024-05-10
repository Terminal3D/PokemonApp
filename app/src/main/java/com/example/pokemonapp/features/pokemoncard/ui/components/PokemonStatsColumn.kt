package com.example.pokemonapp.features.pokemoncard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonStat

@Composable
fun PokemonStatsColumn(stats: Map<String, PokemonStat>) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Box() {
            Text(text = "Stats", style = MaterialTheme.typography.headlineLarge)
        }
        stats.forEach { (_, stat) ->
            PokemonStatsRow(statName = stat.name, statValue = stat.value, iconSrc = stat.iconSrc)
        }
    }
}

@Composable
fun PokemonStatsRow(statName: String, statValue: Int, iconSrc: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.heightIn(min = 24.dp)
    ) {

        Icon(
            painter = painterResource(id = iconSrc),
            contentDescription = null,
            modifier = Modifier.weight(0.1f)
        )
        Box(
            modifier = Modifier.weight(0.4f),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = statName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Box(
            modifier = Modifier.weight(0.5f),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(text = statValue.toString(), style = MaterialTheme.typography.titleLarge)
        }

    }
}

@Preview
@Composable
fun PokemonStatsRowPreview() {
    PokemonStatsRow(statName = "Stat", statValue = 100, iconSrc = R.drawable.ic_attack)
}