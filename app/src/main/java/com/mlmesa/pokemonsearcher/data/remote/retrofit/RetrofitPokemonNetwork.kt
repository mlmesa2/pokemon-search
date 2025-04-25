package com.mlmesa.pokemonsearcher.data.remote.retrofit

import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.data.remote.PokemonRemoteDataSource
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonDetailResponse
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonListResponse
import com.mlmesa.pokemonsearcher.data.remote.safeApiCall
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val POKEMON_BASE_URL = "https://pokeapi.co/api/v2/"
@Singleton
class RetrofitPokemonNetwork @Inject constructor(
    networkJson: Json,
    pokemonCallFactory: dagger.Lazy<Call.Factory>
): PokemonRemoteDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(POKEMON_BASE_URL)
        .callFactory { pokemonCallFactory.get().newCall(it) }
        .addConverterFactory(
            ScalarsConverterFactory.create()
        )
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(PokemonApiService::class.java)

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Result<PokemonListResponse> = safeApiCall {
        networkApi.getPokemonList(limit, offset)
    }

    override suspend fun getPokemonDetail(nameOrId: String): Result<PokemonDetailResponse> = safeApiCall {
        networkApi.getPokemonDetail(nameOrId)
    }

}
