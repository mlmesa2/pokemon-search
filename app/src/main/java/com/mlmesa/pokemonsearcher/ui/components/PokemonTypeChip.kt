package com.mlmesa.pokemonsearcher.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mlmesa.pokemonsearcher.ui.theme.PokemonSearcherTheme

@Composable
fun PokemonTypeChip(type: String) {
    val backgroundColor = when (type.lowercase()) {
        "fire" -> Color(0xFFFF7F50)
        "water" -> Color(0xFF6495ED)
        "grass" -> Color(0xFF98FB98)
        "electric" -> Color(0xFFFFD700)
        "psychic" -> Color(0xFFEE82EE)
        "ice" -> Color(0xFFADD8E6)
        "dragon" -> Color(0xFF7B68EE)
        "dark" -> Color(0xFF696969)
        "fairy" -> Color(0xFFFFB6C1)
        "normal" -> Color(0xFFA9A9A9)
        "fighting" -> Color(0xFFCD5C5C)
        "flying" -> Color(0xFF87CEEB)
        "poison" -> Color(0xFF9370DB)
        "ground" -> Color(0xFFDEB887)
        "rock" -> Color(0xFFDCDCDC)
        "bug" -> Color(0xFF9ACD32)
        "ghost" -> Color(0xFF9370DB)
        "steel" -> Color(0xFFB0C4DE)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        contentColor = Color.Black
    ) {
        Text(
            text = type.replaceFirstChar { it.uppercase() },
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@PreviewLightDark
@Composable
fun PokemonTypeChipPreview() {
    PokemonSearcherTheme {
        PokemonTypeChip(type = "Fire")
    }
}