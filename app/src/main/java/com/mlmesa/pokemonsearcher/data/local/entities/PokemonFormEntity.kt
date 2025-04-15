package com.mlmesa.pokemonsearcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_forms")
data class PokemonFormEntity(
    @PrimaryKey
    val id: Int,
    val pokemonId: Int,
    val name: String,
    val imageUrl: String
)