package com.mlmesa.pokemonsearcher.data.local.converters

import androidx.room.TypeConverter
import com.mlmesa.pokemonsearcher.data.local.PokemonStatLocal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StatConverter {

    @TypeConverter
    fun fromStatList(value: List<PokemonStatLocal>): String = Json.encodeToString(value)

    @TypeConverter
    fun toStatList(value: String): List<PokemonStatLocal> = Json.decodeFromString(value)
}