package com.mlmesa.pokemonsearcher.domain.models

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val abilities: List<Ability>,
    val sprites: Sprites
)

data class Ability(
    val name: String,
    val isHidden: Boolean
)

data class Sprites(
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