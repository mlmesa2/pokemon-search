package com.mlmesa.pokemonsearcher.data.local

import com.mlmesa.pokemonsearcher.domain.models.Stat
import kotlinx.serialization.Serializable

@Serializable
data class PokemonStatLocal(
    val statName: String,
    val baseStat: Int
) {
    fun toDomainModel(): Stat = Stat(
        statName = statName,
        baseStat = baseStat
    )

    companion object {
        fun pokemonStatLocalFromDomain(stat: Stat): PokemonStatLocal = PokemonStatLocal(
            statName = stat.statName,
            baseStat = stat.baseStat
        )
    }
}