package com.mlmesa.pokemonsearcher.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonFormResponse(
    val id: Int,
    val name: String,
    val sprites: FormSpritesResponse
)

@Serializable
data class FormSpritesResponse(
    @SerialName("front_default")
    val frontDefault: String?,
    val other: FormOtherResponse?
)

@Serializable
data class FormOtherResponse(
    @SerialName("official-artwork")
    val officialArtwork: FormOfficialArtworkResponse?
)

@Serializable
data class FormOfficialArtworkResponse(
    @SerialName("front_default")
    val frontDefault: String?
)