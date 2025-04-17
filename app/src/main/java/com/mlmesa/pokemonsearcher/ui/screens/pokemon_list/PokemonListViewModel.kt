package com.mlmesa.pokemonsearcher.ui.screens.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.domain.models.Pokemon
import com.mlmesa.pokemonsearcher.domain.use_cases.GetPokemonListUseCase
import com.mlmesa.pokemonsearcher.domain.use_cases.SearchPokemonUseCase
import com.mlmesa.pokemonsearcher.domain.use_cases.SearchWithIndexUseCase
import com.mlmesa.pokemonsearcher.ui.screens.pokemon_list.PokemonUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PokemonUiState() {
    object Loading : PokemonUiState()
    data class Success(val pokemons: List<Pokemon>) : PokemonUiState()
    data class Error(val message: String) : PokemonUiState()
}

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    val getPokemonListUseCase: GetPokemonListUseCase,
    val searchPokemonUseCase: SearchPokemonUseCase,
    val searchWithIndexUseCase: SearchWithIndexUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonUiState> = MutableStateFlow<PokemonUiState>(Loading)
    val uiState = _uiState
        .onStart {
            loadPokemonList()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Loading
        )

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    fun clearQuery(){
        _searchQuery.value = ""
    }

    fun loadPokemonList(limit: Int = 100, offset: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = Loading
            getPokemonListUseCase(limit, offset).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.value = Success(result.data)
                    }

                    is Result.Error -> {
                        _uiState.value = Error(result.exception?.message ?: "Unknown error")
                    }

                    is Result.Loading, Result.Empty -> {
                        _uiState.value = Loading
                    }
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            _searchQuery.value = ""
            loadPokemonList()
        } else {
            filterPokemonByNameStart(query)
        }
    }
    fun filterPokemonByNameStart(prefix: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = Loading
            searchWithIndexUseCase(prefix).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.value = Success(result.data)
                    }

                    is Result.Error -> {
                        _uiState.value = Error(result.exception?.message ?: "Unknown error")
                    }

                    is Result.Loading, Result.Empty -> {
                        _uiState.value = Loading
                    }
                }
            }
        }
    }

    fun searchPokemon(name: String) {
        _searchQuery.value = name
        if (name.isBlank()){
            loadPokemonList()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = Loading
            searchPokemonUseCase(name.lowercase()).collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.value = Success(result.data)
                    }

                    else -> {
                        if (result is Result.Error) {
                            _uiState.value = Error(
                                if (result.exception?.message?.contains("404") == true) {
                                    "No Pokemon found with name: $name"
                                } else {
                                    result.exception?.message ?: "Unknown error"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}