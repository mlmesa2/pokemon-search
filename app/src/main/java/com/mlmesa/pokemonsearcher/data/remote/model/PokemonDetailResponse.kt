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
    val sprites: SpritesResponse,
    val stats: List<StatResponse>,
    val types: List<TypeResponse>,
    val cries: CriesResponse?
)

@Serializable
data class AbilityResponse(
    val ability: NamedResource,
    @SerialName("is_hidden")
    val isHidden: Boolean
)

@Serializable
data class NamedResource(
    val name: String,
    val url: String
)

@Serializable
data class SpritesResponse(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String?,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
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

@Serializable
data class StatResponse(
    val stat: StatRes,
    @SerialName("base_stat")
    val baseStat: Int
)

@Serializable
data class StatRes(
    val name: String
)

@Serializable
data class TypeResponse(
    val type: TypeRes
)

@Serializable
data class TypeRes(
    val name: String
)

@Serializable
data class CriesResponse(
    val latest: String?,
    val legacy: String?
)