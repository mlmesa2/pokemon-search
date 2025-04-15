package com.mlmesa.pokemonsearcher.domain.use_cases

import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.domain.models.PokemonDetail
import com.mlmesa.pokemonsearcher.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){
    suspend operator fun invoke(nameOrId: String): Flow<Result<PokemonDetail>> = pokemonRepository.getPokemonDetail(nameOrId)
}