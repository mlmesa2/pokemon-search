package com.mlmesa.pokemonsearcher.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<AbilityResponse>,
    val forms: List<FormResponse>,
    val sprites: SpritesResponse
)

@Serializable
data class AbilityResponse(
    val ability: NamedResource,
    @SerialName("is_hidden")
    val isHidden: Boolean
)

@Serializable
data class FormResponse(
    val name: String,
    val url: String
)

@Serializable
data class NamedResource(
    val name: String,
    val url: String
)

@Serializable
data class SpritesResponse(
    @SerialName("front_default")
    val frontDefault: String?,
    val other: OtherResponse?
)

@Serializable
data class OtherResponse(
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtworkResponse?
)

@Serializable
data class OfficialArtworkResponse(
    @SerialName("front_default")
    val frontDefault: String?
)
