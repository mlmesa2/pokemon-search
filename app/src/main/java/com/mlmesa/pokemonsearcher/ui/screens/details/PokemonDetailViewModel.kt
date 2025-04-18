package com.mlmesa.pokemonsearcher.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.domain.models.PokemonDetail
import com.mlmesa.pokemonsearcher.domain.use_cases.GetPokemonDetailUseCase
import com.mlmesa.pokemonsearcher.navigation.PokemonDetailScreenNavigation
import com.mlmesa.pokemonsearcher.ui.screens.details.PokemonDetailUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PokemonDetailUiState {
    object Loading : PokemonDetailUiState
    data class Success(val pokemonDetail: PokemonDetail) : PokemonDetailUiState
    data class Error(val message: String) : PokemonDetailUiState
    object Empty : PokemonDetailUiState
}

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow<PokemonDetailUiState>(Loading)
    val uiState = _uiState
        .onStart {
            loadPokemonDetail()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Loading
        )

    fun loadPokemonDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = Loading
            getPokemonDetailUseCase(savedStateHandle.toRoute<PokemonDetailScreenNavigation>().nameOrId).collect { pokemonDetail ->
                when (pokemonDetail) {
                    is Result.Success -> {
                        _uiState.value = Success(pokemonDetail.data)
                    }

                    is Result.Error -> {
                        _uiState.value = Error(pokemonDetail.exception?.message ?: "Unknown error")
                    }

                    is Result.Loading -> {
                        _uiState.value = Loading
                    }

                    else -> {
                        _uiState.value = Empty
                    }
                }
            }
        }
    }
}