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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    // Aqui criamos um 'remember' de valor boolean
    var mostrarImagem by remember { mutableStateOf(true) }
    var cidade by remember { mutableStateOf("") }
    var cidadeInvalida by remember { mutableStateOf(false) }

    Column(modifier = Modifier) { // Column organiza um embaixo do outro
        Text(
            text = "Primeira aplicação $name!",
            style = TextStyle(
                fontSize = 25.sp, // sp -> unidade para uso de tamanho de fonte
                color = Color(156, 39, 176, 255),
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            )
        )
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
                Text(text = "Aprendendo a criar uma", fontSize = 20.sp)
                // Dá um espaço na horizontal quando estiver em uma Row
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "aplicação Android em Kotlin", fontSize = 20.sp)
            }
        }
        Column(
            modifier = modifier.fillMaxWidth(), // dp -> unidade para o uso de dimensões e desenhos
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { mostrarImagem = false }) {
                Text(text="Esconder Imagem")
            }
                /*
                A imagem...
                Não pode ter letras maiúsculas
                Não pode ter caracteres especial
                Não pode ter espaços em branco
                Não pode ter hífen
                Não pode começar com número
                */
            if (mostrarImagem) {
                Image(
                    painter = painterResource(id = R.mipmap.doraaventureira),
                    contentDescription = "The Rock Dora Aventureira",
                    modifier = modifier.size(250.dp)
                )
            }
            Button(onClick = { mostrarImagem = true }) {
                Text(text="Mostrar Imagem")
            }

            OutlinedTextField(
                label = { Text("Qual a cidade?") },
                value = cidade, // Seu valor será o do 'remember' cidade
                onValueChange = { cidade = it },
                // O valor do 'remember' cidade se altera conforme o que for digitado
                supportingText = {
                    cidadeInvalida = (cidade.isNotBlank() && cidade.length < 3)
                    if (cidadeInvalida) {
                        Text(text = "Cidade ter 3 ou mais letras")
                    }
                },
                isError = cidadeInvalida
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