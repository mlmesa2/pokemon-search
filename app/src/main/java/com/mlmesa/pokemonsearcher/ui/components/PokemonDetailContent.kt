package com.mlmesa.pokemonsearcher.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import com.mlmesa.pokemonsearcher.R
import com.mlmesa.pokemonsearcher.domain.models.PokemonDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PokemonDetailContent(
    pokemonDetail: PokemonDetail,
    scrollState: ScrollState,
    audioUrl: String
) {
    val mainColor = when (pokemonDetail.types.firstOrNull()?.lowercase()) {
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

    val infiniteAnimation = rememberInfiniteTransition()
    val floatAnim by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val player = remember {
        ExoPlayer.Builder(context).build()
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor.copy(alpha = 0.1f))
            .verticalScroll(scrollState)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(mainColor.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = pokemonDetail.imageUrl,
                contentDescription = "Pokemon ${pokemonDetail.name}",
                modifier = Modifier
                    .size(220.dp)
                    .graphicsLayer {
                        translationY = floatAnim
                    }.clickable{
                        coroutineScope.launch {
                            val mediaItem = MediaItem.fromUri(audioUrl)
                            player.setMediaItem(mediaItem)
                            player.prepare()
                            player.playWhenReady = true
                        }
                    },
                contentScale = ContentScale.Fit,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pokemonDetail.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "#${pokemonDetail.id}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.types),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pokemonDetail.types.forEach { type ->
                    PokemonTypeChip(type = type)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCard(
                    title = stringResource(R.string.height),
                    value = "${pokemonDetail.height / 10.0} m",
                    modifier = Modifier.weight(1f),
                    mainColor = mainColor
                )
                InfoCard(
                    title = stringResource(R.string.weight),
                    value = "${pokemonDetail.weight / 10.0} kg",
                    modifier = Modifier.weight(1f),
                    mainColor = mainColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.abilities),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                pokemonDetail.abilities.forEach { ability ->
                    AbilityItem(
                        abilityName = ability.name,
                        mainColor = mainColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.sprites),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {

                val sprites = listOfNotNull(
                    pokemonDetail.sprites.backDefault,
                    pokemonDetail.sprites.frontDefault,
                    pokemonDetail.sprites.backShiny,
                    pokemonDetail.sprites.frontShiny,
                    pokemonDetail.sprites.backFemale,
                    pokemonDetail.sprites.frontFemale,
                    pokemonDetail.sprites.backShinyFemale,
                    pokemonDetail.sprites.frontShinyFemale,
                    pokemonDetail.sprites.officialArtwork
                ).filterNot { it.isBlank() }

                items(sprites) { sprite ->
                    PokemonSpritesItem(imageUrl = sprite)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.base_stats),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pokemonDetail.stats.forEach { (statName, value) ->
                    StatBar(
                        statName = statName,
                        value = value,
                        maxValue = 200,
                        mainColor = mainColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
