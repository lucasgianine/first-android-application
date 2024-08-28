package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

/*
Activity -> Tudo aquilo que toma o 1º plano do dispositivo
99% das vezes é uma "tela" de aplicativo
*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tela("Android")
                }
            }
        }
    }
}

/*
@Composable -> Indica que a função contém um elemento de tela (UI)
que pode ser algo simples ou bem complexo

Funções anotadas com ela seguem a convenção PascalCase
*/
@Composable
fun Tela(name: String, modifier: Modifier = Modifier) { // modifier: CSS do componente
    Column(modifier = Modifier) { // Column organiza um embaixo do outro
        Row {
            Text(
                text = "Hello, $name!",
                style = TextStyle(
                    fontSize = 25.sp, // sp -> unidade para uso de tamanho de fonte
                    color = Color.Blue,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(text = "Segunda linha", color = Color(156, 39, 176, 255))
        }
        Row(
            modifier = modifier.padding(25.dp), // dp -> unidade para o uso de dimensões e desenhos
            horizontalArrangement = Arrangement.Center
            // Distribui igualmente a partir do centro
        ) {
            Column(
                // Alinhamento centralizado na horizontal
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.weight(0.5f) // Indica quantos % do "pai" será ocupada
            ) {
                Text(text = "Linha 2, coluna 1", fontSize = 20.sp)
                // Dá um espaço na horizontal quando estiver em uma Row
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Linha 3, coluna 1", fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.weight(0.5f)
            ) {
                Text(text = "Linha 2, coluna 2", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Linha 3, coluna 2", fontSize = 20.sp)
            }
        }
        Column(
            modifier = modifier.fillMaxWidth(), // dp -> unidade para o uso de dimensões e desenhos
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { }) {
                Text(text="Clica aqui")
            }
            Image(
                painter = painterResource(id = R.mipmap.doraaventureira),
                contentDescription = "The Rock Dora Aventureira",
                modifier = modifier.size(250.dp)
            )
        }
    }
}

// showSystemUi = true -> Mostra um tamanho próximo a um celular no preview
// @Preview -> Indica qual função será usada para preencher o Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Tela("Android")
    }
}