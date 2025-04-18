package com.mlmesa.pokemonsearcher.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mlmesa.pokemonsearcher.ui.screens.details.PokemonDetailRoute
import com.mlmesa.pokemonsearcher.ui.screens.pokemon_list.PokemonListRoute
import kotlinx.serialization.Serializable

@Serializable
data object PokemonScreenNavigation

@Serializable
data class PokemonDetailScreenNavigation(
    val nameOrId: String
)

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    appState: PokemonAppState
) {
    val navHostController = rememberNavController()
     NavHost(
         navController = navHostController,
         startDestination = PokemonScreenNavigation,
         modifier = modifier
    ) {
         composable<PokemonScreenNavigation> {
             PokemonListRoute(
                 navigateToDetails = { pokemonName ->
                     navHostController.navigate(PokemonDetailScreenNavigation(pokemonName))
                 }
             )
         }

         composable<PokemonDetailScreenNavigation>{
             PokemonDetailRoute(
                 onBackState = { navHostController.popBackStack() }
             )
         }
     }
}