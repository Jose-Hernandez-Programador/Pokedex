package com.example.pokedex
import retrofit2.http.GET
interface ApiService {

    @GET("pokemon")
    suspend fun getPokemon(): PokemonResultados


}

