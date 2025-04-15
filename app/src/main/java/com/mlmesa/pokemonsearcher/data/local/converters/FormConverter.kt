package com.mlmesa.pokemonsearcher.data.local.converters

import androidx.room.TypeConverter
import com.mlmesa.pokemonsearcher.data.local.PokemonDetailForm
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FormConverter {

    @TypeConverter
    fun fromFormList(value: List<PokemonDetailForm>): String = Json.encodeToString(value)

    @TypeConverter
    fun toFormList(value: String): List<PokemonDetailForm> = Json.decodeFromString(value)

}