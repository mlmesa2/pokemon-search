package com.mlmesa.pokemonsearcher.data.local

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailForm(
    val name: String,
    val imageUrl: String
)