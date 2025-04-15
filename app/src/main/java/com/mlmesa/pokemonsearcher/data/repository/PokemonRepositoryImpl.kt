package com.mlmesa.pokemonsearcher.data.repository

import com.mlmesa.pokemonsearcher.common.BASE_IMAGE_URL
import com.mlmesa.pokemonsearcher.common.EXTENSION_IMAGE_URL
import com.mlmesa.pokemonsearcher.common.Result
import com.mlmesa.pokemonsearcher.data.local.dao.PokemonSearchDao
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonEntity.Companion.pokemonEntityFromDomain
import com.mlmesa.pokemonsearcher.data.remote.PokemonRemoteDataSource
import com.mlmesa.pokemonsearcher.data.remote.toDetailEntity
import com.mlmesa.pokemonsearcher.data.remote.toPokemonEntity
import com.mlmesa.pokemonsearcher.domain.models.Pokemon
import com.mlmesa.pokemonsearcher.domain.models.PokemonDetail
import com.mlmesa.pokemonsearcher.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonSearchDao: PokemonSearchDao
) : PokemonRepository {
    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Result<List<Pokemon>>> = flow {
        pokemonSearchDao.getPokemonList(limit, offset).collect { localPokemons ->
            if (localPokemons.isEmpty()) {
                emit(Result.Loading)
            } else {
                emit(Result.Success(localPokemons.map { it.toDomainModel() }))
            }
        }

        when (val response = pokemonRemoteDataSource.getPokemonList(limit, offset)) {
            is Result.Success -> {
                if (response.data.results.isNotEmpty()) {
                    pokemonSearchDao.insertPokemons(response.data.results.map {
                        var id: Int = extractPokemonIdFromUrl(it.url)
                        var url: String = "$BASE_IMAGE_URL$id$EXTENSION_IMAGE_URL"
                        pokemonEntityFromDomain(Pokemon(id, it.name, url))
                    })

                    pokemonSearchDao.getPokemonList(limit, offset).collect { updatedLocalPokemons ->
                        emit(Result.Success(updatedLocalPokemons.map { it.toDomainModel() }))
                    }
                }
            }

            is Result.Error ->
                emit(Result.Error(response.exception))

            else ->
                emit(Result.Empty)
        }
    }

    override suspend fun getPokemonDetail(nameOrId: String): Flow<Result<PokemonDetail>> = flow {

        pokemonSearchDao.getPokemonDetail(name = nameOrId).collect {
            if (it == null) {
                emit(Result.Loading)
            } else {
                emit(Result.Success(it.toDetailDomainModel()))
            }

            when (val response = pokemonRemoteDataSource.getPokemonDetail(nameOrId)) {
                is Result.Success -> {
                    pokemonSearchDao.insertPokemonDetail(response.data.toDetailEntity())
                    pokemonSearchDao.getPokemonDetail(name = nameOrId).collect {
                        emit(Result.Success(it!!.toDetailDomainModel()))
                    }
                }

                is Result.Error ->
                    emit(Result.Error(response.exception))

                else ->
                    emit(Result.Empty)
            }

        }
    }

    override suspend fun searchPokemon(query: String): Flow<Result<List<Pokemon>>> = flow {
        pokemonSearchDao.searchPokemon(query).collect { localPokemons ->
            if (localPokemons.isEmpty()) {
                emit(Result.Loading)
            } else {
                emit(Result.Success(localPokemons.map { it.toDomainModel() }))
            }

            when (val response = pokemonRemoteDataSource.getPokemonDetail(query)) {
                is Result.Success -> {
                    pokemonSearchDao.insertOnePokemon(response.data.toPokemonEntity())
                    pokemonSearchDao.searchPokemon(query).collect { updatedLocalPokemons ->
                        emit(Result.Success(updatedLocalPokemons.map { it.toDomainModel() }))
                    }
                }

                is Result.Error ->
                    emit(Result.Error(response.exception))

                else ->
                    emit(Result.Empty)
            }
        }
    }


    override suspend fun filterPokemonByNameStart(prefix: String): Flow<Result<List<Pokemon>>> = flow {
            pokemonSearchDao.searchPokemonByNameStart(prefix).collect { localPokemons ->
                emit(Result.Success(localPokemons.map { it.toDomainModel() }))
            }
        }

}
