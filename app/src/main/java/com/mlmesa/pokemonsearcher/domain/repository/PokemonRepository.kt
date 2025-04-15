package com.mlmesa.pokemonsearcher.domain.repository

import com.mlmesa.pokemonsearcher.domain.models.Pokemon
import com.mlmesa.pokemonsearcher.domain.models.PokemonDetail
import kotlinx.coroutines.flow.Flow
import com.mlmesa.pokemonsearcher.common.Result

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Flow<Result<List<Pokemon>>>
    suspend fun getPokemonDetail(nameOrId: String): Flow<Result<PokemonDetail>>
    suspend fun searchPokemon(query: String): Flow<Result<List<Pokemon>>>
    suspend fun filterPokemonByNameStart(prefix: String): Flow<Result<List<Pokemon>>>
}