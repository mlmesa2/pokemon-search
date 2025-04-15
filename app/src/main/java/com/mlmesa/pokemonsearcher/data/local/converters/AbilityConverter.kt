package com.mlmesa.pokemonsearcher.data.local.converters

import androidx.room.TypeConverter
import com.mlmesa.pokemonsearcher.data.local.PokemonDetailAbility
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AbilityConverter {

    @TypeConverter
    fun fromAbilityList(value: List<PokemonDetailAbility>): String = Json.encodeToString(value)

    @TypeConverter
    fun toAbilityList(value: String): List<PokemonDetailAbility> = Json.decodeFromString(value)

}