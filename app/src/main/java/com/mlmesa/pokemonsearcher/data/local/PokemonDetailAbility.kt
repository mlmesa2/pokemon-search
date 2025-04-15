package com.mlmesa.pokemonsearcher.data.local

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailAbility(
    val name: String,
    val isHidden: Boolean
)