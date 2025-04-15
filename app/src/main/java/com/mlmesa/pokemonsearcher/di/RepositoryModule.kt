package com.mlmesa.pokemonsearcher.di

import com.mlmesa.pokemonsearcher.data.local.dao.PokemonSearchDao
import com.mlmesa.pokemonsearcher.data.remote.PokemonRemoteDataSource
import com.mlmesa.pokemonsearcher.data.repository.PokemonRepositoryImpl
import com.mlmesa.pokemonsearcher.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonRepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        pokemonSearchDao: PokemonSearchDao
    ): PokemonRepository = PokemonRepositoryImpl(pokemonRemoteDataSource, pokemonSearchDao)
}