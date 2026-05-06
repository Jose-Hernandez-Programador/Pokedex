package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel


class DatosPokemon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        Pantalla()
        }
    }
}
 @Preview
@Composable

 fun Pantalla(){
     Box(modifier = Modifier.fillMaxSize()){
         ContenidoPokemon()
         Text(text ="Create by Jose Programador",
             fontSize = 12.sp,
             fontWeight= FontWeight.Black,
             color = Color.Black,
             modifier = Modifier
                 .align (Alignment.BottomStart)
                 .padding(10.dp)
                 .navigationBarsPadding()
         )

         Text(
             text ="Version 0.0.1",
             fontSize = 12.sp,
             fontWeight= FontWeight.Black,
             color = Color.Black,
             modifier = Modifier
                 .align (Alignment.BottomEnd)
                 .padding(10.dp)
                 .navigationBarsPadding()
         )
     }
 }


@OptIn(ExperimentalMaterial3Api::class)

@Preview
@Composable
fun ContenidoPokemon(viewModel: ViewModelPokemon = viewModel()) {
    Scaffold(
        topBar = {
            /*Barra superior*/
            TopAppBar(
                {
                    Text(
                        text = "Datos Pokemons",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFe51f25),
                    titleContentColor = Color.White
                )
            )
        },


        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.obtenerPokemons()},
                modifier = Modifier.navigationBarsPadding())
            {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "↻")
            }
        }

    ) { PaddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize()
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                painter = painterResource(id = R.drawable.fondopantalla),
                contentDescription = "Fondo de Pantalla",
                contentScale = ContentScale.Crop //permite que la imagen cubra toda la pantalla
            )

            Column(
                modifier = Modifier
                    .padding(PaddingValues)//Sirve para que el contenido no se vea afectado por la barra superior
            ) {
            InformacionPokemon()
            }
        }
    }
}

@Preview
 @Composable
fun InformacionPokemon(viewModel: ViewModelPokemon =viewModel(),
                       modifier: Modifier = Modifier)//conecta la vista con el ViewModel
//modifier permite que desde afuera puedas modificar el diseño
{

    val listaPokemon = viewModel.pokemonList.value //obtiene la lista de usuarios desde el ViewModel
    val cargando = viewModel.cargando.value // trae el estado de cargando desde el ViewModel

    Box(
        //se usa box para superponer la pantalla de loader a la del contenido
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (cargando) {

            CircularProgressIndicator(
                color = Color.Red, //cambia el color del circulo del loader
                strokeWidth = 5.dp //cambia el grosor del circulo del loader
            ) //si esta cargando muestra un circulo girando

        } else {
            LazyColumn(modifier = Modifier.navigationBarsPadding()) {

                items(listaPokemon,key={it.name})/*recorre la lista de Pokemon*/
                { pokemon -> /*representa cada elemento de la lista*/
                    //pokemon es una variable lambda que contiene cada elemento de la lista y puede llevar cualquier nombre

                    //  1. Sacar ID del Pokémon
                    val id = pokemon.url
                        .split("/")
                        .filter { it.isNotEmpty() }
                        .last()

                    //  2. Crear URL de imagen
                    val imageUrl =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE51F25)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp,
                                alignment = Alignment.CenterVertically)
                        )
                        {

                            // Imagen del Pokemon
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = pokemon.name,
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(8.dp)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = pokemon.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )

                        }
                    }
                }
            }
        }
    }
}