package com.mlmesa.pokemonsearcher.ui.screens.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mlmesa.pokemonsearcher.R
import com.mlmesa.pokemonsearcher.domain.models.Pokemon
import com.mlmesa.pokemonsearcher.ui.components.PokemonList
import com.mlmesa.pokemonsearcher.ui.components.SearchBar
import com.mlmesa.pokemonsearcher.ui.theme.PokemonSearcherTheme


@Composable
fun PokemonListRoute(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    PokemonListScreen(
        modifier =modifier,
        uiState = uiState,
        searchQuery = searchQuery,
        navigateToDetails = navigateToDetails,
        searchPokemon = viewModel::searchPokemon,
        loadPokemons = viewModel::loadPokemonList,
        updateSearchQuery = viewModel::updateSearchQuery,
        clearQuery = viewModel::clearQuery
    )
}

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    uiState: PokemonUiState,
    searchQuery: String,
    navigateToDetails: (String) -> Unit,
    searchPokemon : (String) -> Unit,
    loadPokemons : () -> Unit,
    updateSearchQuery : (String) -> Unit,
    clearQuery : () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isFilterMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            query = searchQuery,
            filterMode = isFilterMode,
            onQueryChange = { if (isFilterMode) searchPokemon(it) else updateSearchQuery(it) },
            onSearch = {
                searchPokemon(searchQuery)
                focusManager.clearFocus()
            },
            onClearQuery = {
                clearQuery()
                focusManager.clearFocus()
                loadPokemons()
            },
            focusRequester = focusRequester
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.filter_search),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = isFilterMode, onCheckedChange = {
                isFilterMode  = it
            })
        }
        Spacer(modifier = Modifier.height(4.dp))

        when (uiState) {
            is PokemonUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            is PokemonUiState.Success -> {
                val pokemonList = uiState.pokemons
                if (pokemonList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_pok_mon_found),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                } else {
                    PokemonList(
                        pokemons = pokemonList,
                        onPokemonClick = {
                            navigateToDetails(it.name)
                            clearQuery()
                        }
                    )
                }
            }
            is PokemonUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = uiState.message,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { loadPokemons() }) {
                            Text(stringResource(R.string.retry))
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
fun PokemonListScreenPreview() {
    PokemonSearcherTheme {
        PokemonListScreen(
            uiState = PokemonUiState.Success(
                pokemons = listOf(
                    Pokemon(
                        id = 1,
                        name = "Bulbasaur",
                        imageUrl = ""
                    ),
                    Pokemon(
                        id = 25,
                        name = "Pikachu",
                        imageUrl = ""
                    ),
                    Pokemon(
                        id = 132,
                        name = "Ditto",
                        imageUrl = ""
                    ),
                    Pokemon(
                        id = 152,
                        name = "Chikorita",
                        imageUrl = ""
                    )
                )
            ),
            searchQuery = "",
            navigateToDetails = {},
            searchPokemon = {},
            loadPokemons = {},
            updateSearchQuery = {},
            clearQuery = {}
        )
    }
}