package com.mlmesa.pokemonsearcher.domain.use_cases

import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.domain.models.Pokemon
import com.mlmesa.pokemonsearcher.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchWithIndexUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){
    suspend operator fun invoke(query: String): Flow<Result<List<Pokemon>>> = pokemonRepository.searchPokemon(query)
}