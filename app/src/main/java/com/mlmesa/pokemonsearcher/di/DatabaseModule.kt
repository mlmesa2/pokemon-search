package com.mlmesa.pokemonsearcher.di

import android.content.Context
import androidx.room.Room
import com.mlmesa.pokemonsearcher.data.local.PokemonSearchDatabase
import com.mlmesa.pokemonsearcher.data.local.dao.PokemonSearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePokemonSearchDatabase(
        @ApplicationContext context: Context,
    ): PokemonSearchDatabase = Room.databaseBuilder(
        context = context,
        klass = PokemonSearchDatabase::class.java,
        name = "pokemondb",
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesPokemonSearchDao(
        database: PokemonSearchDatabase,
    ): PokemonSearchDao = database.pokemonSearchDao()
}