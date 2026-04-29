package com.example.pokedex


data class PokemonResultados(
    val results: List<ClasePokemon>)

data class ClasePokemon(
    val url: String,
    val name: String
)
