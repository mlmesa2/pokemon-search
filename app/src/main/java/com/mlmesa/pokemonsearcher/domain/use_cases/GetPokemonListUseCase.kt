package com.mlmesa.pokemonsearcher.domain.use_cases

import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.domain.models.Pokemon
import com.mlmesa.pokemonsearcher.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): Flow<Result<List<Pokemon>>> = pokemonRepository.getPokemonList(limit, offset)
}