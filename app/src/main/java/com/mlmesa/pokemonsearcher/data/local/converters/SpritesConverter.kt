package com.mlmesa.pokemonsearcher.data.local.converters

import androidx.room.TypeConverter
import com.mlmesa.pokemonsearcher.data.local.PokemonSprites
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SpritesConverter {

    @TypeConverter
    fun fromSprites(sprites: PokemonSprites?): String = Json.encodeToString(sprites)

    @TypeConverter
    fun toSprites(data: String?): PokemonSprites? = Json.decodeFromString(data.toString())

}