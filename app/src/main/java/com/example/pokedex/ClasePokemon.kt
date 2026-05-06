package com.example.pokedex


//Clase que contiene la informacion de los pokemons
data class ClasePokemon(
    val url: String,
    val name: String
)

//Clase que contiene la lista de pokemons
data class PokemonResultados(
    val results: List<ClasePokemon>)

