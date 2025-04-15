package com.mlmesa.pokemonsearcher.data.local

import com.mlmesa.pokemonsearcher.domain.models.Ability
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailAbility(
    val name: String,
    val isHidden: Boolean
) {
    fun toDomainModel(): Ability = Ability(
        name = name,
        isHidden = isHidden
    )

    companion object {
        fun pokemonDetailAbilityFromDomain(ability: Ability): PokemonDetailAbility =
            PokemonDetailAbility(
                name = ability.name,
                isHidden = ability.isHidden
            )
    }
}