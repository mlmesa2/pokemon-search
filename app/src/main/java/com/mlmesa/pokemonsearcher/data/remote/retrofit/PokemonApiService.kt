package com.mlmesa.pokemonsearcher.data.remote.retrofit

import com.mlmesa.pokemonsearcher.data.remote.model.PokemonDetailResponse
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonFormResponse
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET(value = "pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonListResponse>

    @GET("pokemon/{nameOrId}")
    suspend fun getPokemonDetail(
        @Path("nameOrId") nameOrId: String
    ): Response<PokemonDetailResponse>

    @GET("pokemon-form/{id}")
    suspend fun getPokemonForm(
        @Path("id") id: Int
    ): Response<PokemonFormResponse>
}