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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.POKEDEXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                FondoPantalla()
        }
    }
}

@Preview
@Composable
fun FondoPantalla (){
    Box(modifier = Modifier.fillMaxSize()) {

        ContenidoPantalla()

        Text(
            text ="Create by Jose Programador",
            fontSize = 12.sp,
            fontWeight= FontWeight.Black,
            color = Color.White,
            modifier = Modifier
                .align (Alignment.BottomStart)
                .padding(10.dp)
                .navigationBarsPadding()
        )

        Text(
            text ="Version 0.0.1",
            fontSize = 12.sp,
            fontWeight= FontWeight.Black,
            color = Color.White,
            modifier = Modifier
                .align (Alignment.BottomEnd)
                .padding(10.dp)
                .navigationBarsPadding()
        )
    }
}

@Preview
@Composable
fun ContenidoPantalla() {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize()
                .fillMaxWidth()
                .navigationBarsPadding(),
            painter = painterResource(id = R.drawable.pokedexfondo),
            contentDescription = "Fondo de Pantalla",
            contentScale = ContentScale.Crop //permite que la imagen cubra toda la pantalla
        )

        Column(modifier = Modifier.fillMaxSize()
            .padding(top = 300.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {


            Text(
                text = "Bienvenido Entrenador",
                fontSize = 30.sp,
                fontWeight = FontWeight.Black,
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Aqui podrás obtener informacion de diferentes pokemos",
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(0.8f)//ocupa el 80% del ancho de la pantalla
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp), // bordes redondeados
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFe51f25)
                ),
                        elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp )// sombra
            ) {
                Text(
                    text = "Ver Pokemones",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }
}

