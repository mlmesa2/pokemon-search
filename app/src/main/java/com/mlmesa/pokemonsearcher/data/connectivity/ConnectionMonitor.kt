package com.mlmesa.pokemonsearcher.data.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectionMonitor {
    val isConnected: Flow<Boolean>
}