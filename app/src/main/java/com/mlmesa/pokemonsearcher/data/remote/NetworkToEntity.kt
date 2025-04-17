package com.mlmesa.pokemonsearcher.data.remote

import com.mlmesa.pokemonsearcher.common.BASE_IMAGE_URL
import com.mlmesa.pokemonsearcher.common.EXTENSION_IMAGE_URL
import com.mlmesa.pokemonsearcher.data.local.PokemonDetailAbility
import com.mlmesa.pokemonsearcher.data.local.PokemonSprites
import com.mlmesa.pokemonsearcher.data.local.PokemonStatLocal
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonDetailEntity
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonEntity
import com.mlmesa.pokemonsearcher.data.remote.model.PokemonDetailResponse

fun PokemonDetailResponse.toDetailEntity(): PokemonDetailEntity =
    PokemonDetailEntity(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = sprites.other?.officialArtwork?.frontDefault
            ?: "$BASE_IMAGE_URL$id$EXTENSION_IMAGE_URL",
        abilities = abilities.map {
            PokemonDetailAbility(
                name = it.ability.name,
                isHidden = it.isHidden
            )
        },
        sprites = PokemonSprites(
            backDefault = sprites.backDefault,
            backFemale = sprites.backFemale,
            backShiny = sprites.backShiny,
            backShinyFemale = sprites.backShinyFemale,
            frontDefault = sprites.frontDefault,
            frontFemale = sprites.frontFemale,
            frontShiny = sprites.frontShiny,
            frontShinyFemale = sprites.frontShinyFemale,
            officialArtwork = sprites.other?.officialArtwork?.frontDefault,
        ),
        stats = stats.map {
            PokemonStatLocal(
                statName = it.stat.name,
                baseStat = it.baseStat
            )
        },
        types = types.map { it.type.name }
    )

fun PokemonDetailResponse.toPokemonEntity(): PokemonEntity =
    PokemonEntity(
        id = id,
        name = name,
        imageUrl = sprites.other?.officialArtwork?.frontDefault
            ?: "$BASE_IMAGE_URL$id$EXTENSION_IMAGE_URL",

    )