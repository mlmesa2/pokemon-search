package com.mlmesa.pokemonsearcher.data.local.converters

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TypeConverter {

    @TypeConverter
    fun fromTypeList(value: List<String>): String = Json.encodeToString(value)

    @TypeConverter
    fun toTypeList(value: String): List<String> = Json.decodeFromString(value)

}