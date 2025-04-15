package com.mlmesa.pokemonsearcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mlmesa.pokemonsearcher.data.local.PokemonDetailAbility
import com.mlmesa.pokemonsearcher.data.local.PokemonDetailForm

@Entity(tableName = "pokemon_details")
data class PokemonDetailEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val abilities: List<PokemonDetailAbility>,
    val forms: List<PokemonDetailForm>
)