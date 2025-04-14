package com.mlmesa.pokemonsearcher.data.remote

import com.mlmesa.pokemonsearcher.data.remote.model.PokemonDetailResponse
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonFormResponse
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonListResponse
import com.mlmesa.pokemonsearcher.common.Result

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonListResponse>
    suspend fun getPokemonDetail(nameOrId: String): Result<PokemonDetailResponse>
    suspend fun getPokemonForm(id: Int): Result<PokemonFormResponse>
}