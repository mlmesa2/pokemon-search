package com.mlmesa.pokemonsearcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mlmesa.pokemonsearcher.domain.models.Pokemon

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String
) {
    fun toDomainModel(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
    }

    companion object {
        fun pokemonEntityFromDomain(pokemon: Pokemon): PokemonEntity = PokemonEntity(
            id = pokemon.id,
            name = pokemon.name,
            imageUrl = pokemon.imageUrl
        )
    }
}
