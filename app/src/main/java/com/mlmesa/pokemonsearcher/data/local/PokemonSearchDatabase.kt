package com.mlmesa.pokemonsearcher.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mlmesa.pokemonsearcher.data.local.converters.AbilityConverter
import com.mlmesa.pokemonsearcher.data.local.converters.SpritesConverter
import com.mlmesa.pokemonsearcher.data.local.converters.StatConverter
import com.mlmesa.pokemonsearcher.data.local.converters.TypeConverter
import com.mlmesa.pokemonsearcher.data.local.dao.PokemonSearchDao
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonDetailEntity
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonEntity

@Database(
    entities = [
        PokemonEntity::class,
        PokemonDetailEntity::class,
    ],
    version = 1
)
@TypeConverters(
    AbilityConverter::class,
    SpritesConverter::class,
    StatConverter::class,
    TypeConverter::class
)
abstract class PokemonSearchDatabase : RoomDatabase() {
    abstract fun pokemonSearchDao(): PokemonSearchDao
}