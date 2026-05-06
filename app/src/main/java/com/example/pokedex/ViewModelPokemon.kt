package com.example.pokedex

import android.util.Log.e
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ViewModelPokemon : ViewModel() {
    var pokemonList = mutableStateOf<List<ClasePokemon>>(emptyList())
    var cargando = mutableStateOf(true)
        private set

    init {
        obtenerPokemons()
    }

    fun obtenerPokemons() {
        viewModelScope.launch {
            try {
                cargando.value = true
                val respuesta = ObjectPokemon.api.getPokemon()
                pokemonList.value = respuesta.results
            }
            catch(e: Exception) {
                e.printStackTrace()
            }
            finally {
                cargando.value = false
            }
        }

    }
}


