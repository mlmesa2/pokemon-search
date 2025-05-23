package com.mlmesa.pokemonsearcher.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonDetailEntity
import com.mlmesa.pokemonsearcher.data.local.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnePokemon(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetail(pokemonDetail: PokemonDetailEntity)

    @Query("SELECT * FROM pokemons ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getPokemonList(limit: Int, offset: Int): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_details WHERE id = :id OR name = :name")
    fun getPokemonDetail(id: Int = 0, name: String = ""): Flow<PokemonDetailEntity?>

    @Query("SELECT * FROM pokemons WHERE name LIKE :query || '%'")
    fun searchPokemonByNameStart(query: String): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons WHERE name LIKE '%' || :query || '%'")
    fun searchPokemon(query: String): Flow<List<PokemonEntity>>
}
