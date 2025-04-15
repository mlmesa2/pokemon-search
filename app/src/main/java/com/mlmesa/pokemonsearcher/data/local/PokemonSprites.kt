package com.mlmesa.pokemonsearcher.data.local

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprites(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?,
    val officialArtwork: String?,
)
