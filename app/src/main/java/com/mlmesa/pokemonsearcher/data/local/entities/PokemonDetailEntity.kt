package com.mlmesa.pokemonsearcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mlmesa.pokemonsearcher.data.local.PokemonDetailAbility
import com.mlmesa.pokemonsearcher.data.local.PokemonDetailAbility.Companion.pokemonDetailAbilityFromDomain
import com.mlmesa.pokemonsearcher.data.local.PokemonSprites
import com.mlmesa.pokemonsearcher.data.local.PokemonSprites.Companion.pokemonSpritesFromDomainModel
import com.mlmesa.pokemonsearcher.data.local.PokemonStatLocal
import com.mlmesa.pokemonsearcher.data.local.PokemonStatLocal.Companion.pokemonStatLocalFromDomain
import com.mlmesa.pokemonsearcher.domain.models.PokemonDetail

@Entity(tableName = "pokemon_details")
data class PokemonDetailEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val abilities: List<PokemonDetailAbility>,
    val sprites: PokemonSprites,
    val stats: List<PokemonStatLocal>,
    val types: List<String>
) {
    fun toDetailDomainModel(): PokemonDetail = PokemonDetail(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        abilities = abilities.map { it.toDomainModel() },
        sprites = sprites.toDomainModel(),
        stats = stats.map { it.toDomainModel() },
        types = types
    )

    companion object {
        fun pokemonDetailEntityFromDomainModel(pokemonDetail: PokemonDetail): PokemonDetailEntity =
            PokemonDetailEntity(
                id = pokemonDetail.id,
                name = pokemonDetail.name,
                height = pokemonDetail.height,
                weight = pokemonDetail.weight,
                imageUrl = pokemonDetail.imageUrl,
                abilities = pokemonDetail.abilities.map { pokemonDetailAbilityFromDomain(it) },
                sprites = pokemonSpritesFromDomainModel(pokemonDetail.sprites),
                stats = pokemonDetail.stats.map { pokemonStatLocalFromDomain(it) },
                types = pokemonDetail.types
            )

    }
}