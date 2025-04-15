package com.mlmesa.pokemonsearcher.data.local

import com.mlmesa.pokemonsearcher.domain.models.Sprites
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
) {
    fun toDomainModel(): Sprites = Sprites(
        backDefault = backDefault,
        backFemale = backFemale,
        backShiny = backShiny,
        backShinyFemale = backShinyFemale,
        frontDefault = frontDefault,
        frontFemale = frontFemale,
        frontShiny = frontShiny,
        frontShinyFemale = frontShinyFemale,
        officialArtwork = officialArtwork
    )

    companion object {
        fun pokemonSpritesFromDomainModel(sprites: Sprites): PokemonSprites = PokemonSprites(
            backDefault = sprites.backDefault,
            backFemale = sprites.backFemale,
            backShiny = sprites.backShiny,
            backShinyFemale = sprites.backShinyFemale,
            frontDefault = sprites.frontDefault,
            frontFemale = sprites.frontFemale,
            frontShiny = sprites.frontShiny,
            frontShinyFemale = sprites.frontShinyFemale,
            officialArtwork = sprites.officialArtwork
        )
    }
}
