package com.mlmesa.pokemonsearcher.data.repository

 fun extractPokemonIdFromUrl(url: String): Int {
    val regex = ".*/([0-9]+)/".toRegex()
    val matchResult = regex.find(url)
    return matchResult?.groupValues?.get(1)?.toInt() ?: 0
}