package com.mlmesa.pokemonsearcher.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.mlmesa.pokemonsearcher.data.connectivity.ConnectionMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberAppState(
    networkConnection: ConnectionMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): PokemonAppState {
    return remember(coroutineScope, networkConnection) {
        PokemonAppState(coroutineScope, networkConnection)
    }
}

class  PokemonAppState(
    coroutineScope: CoroutineScope,
    networkConnection: ConnectionMonitor
) {

    val isOffline = networkConnection.isConnected
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}