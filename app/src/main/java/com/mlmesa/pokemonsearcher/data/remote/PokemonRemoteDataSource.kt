package com.mlmesa.pokemonsearcher.data.remote

import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonDetailResponse
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonListResponse

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonListResponse>
    suspend fun getPokemonDetail(nameOrId: String): Result<PokemonDetailResponse>
}