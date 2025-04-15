package com.mlmesa.pokemonsearcher.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mlmesa.pokemonsearcher.data.local.converters.AbilityConverter
import com.mlmesa.pokemonsearcher.data.local.converters.FormConverter
import com.mlmesa.pokemonsearcher.data.local.dao.PokemonSearchDao
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonDetailEntity
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonEntity
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonFormEntity

@Database(
    entities = [
        PokemonEntity::class,
        PokemonDetailEntity::class,
        PokemonFormEntity::class
    ],
    version = 1
)
@TypeConverters(AbilityConverter::class, FormConverter::class)
abstract class PokemonSearchDatabase : RoomDatabase() {
    abstract fun pokemonSearchDao(): PokemonSearchDao
}