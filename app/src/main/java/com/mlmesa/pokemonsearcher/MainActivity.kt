package com.mlmesa.pokemonsearcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mlmesa.pokemonsearcher.data.connectivity.ConnectionMonitor
import com.mlmesa.pokemonsearcher.navigation.PokemonApp
import com.mlmesa.pokemonsearcher.navigation.rememberAppState
import com.mlmesa.pokemonsearcher.ui.theme.PokemonSearcherTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkConnection: ConnectionMonitor

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val appState = rememberAppState(
                networkConnection = networkConnection
            )
            PokemonSearcherTheme {
                PokemonApp(pokemonAppState = appState)

            }
        }
    }
}

