package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class Tela2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Form()
                }
            }
        }
    }
}

@Composable
fun Form(modifier: Modifier = Modifier) {
    var nome by remember { mutableStateOf("") }
    var nomeInvalido by remember { mutableStateOf(false) }
    var rendaMensal by remember { mutableStateOf("") }
    var rendaMensalInvalida by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = { Text("Seu nome") },
            value = nome,
            onValueChange = { nome = it },
            supportingText = {
                nomeInvalido = (nome.isNotBlank() && nome.length < 3)
                if (nomeInvalido) {
                    Text(text = "Nome deve ter 3 ou mais letras")
                }
            },
            isError = nomeInvalido
        )
        Spacer(modifier = Modifier.width(20.dp))
        OutlinedTextField(
            label = { Text("Sua renda mensal") },
            value = rendaMensal,
            onValueChange = { rendaMensal = it },
            supportingText = {
                rendaMensalInvalida = (rendaMensal.isNotBlank() && rendaMensal.toDouble() < 0.0)
                if (rendaMensalInvalida) {
                    Text(text = "Renda mensal deve ser maior que 0")
                }
            },
            isError = rendaMensalInvalida
        )

        Spacer(modifier = Modifier.width(40.dp))

        if (!nomeInvalido && !rendaMensalInvalida && rendaMensal.isNotBlank()) {
            if (rendaMensal.toDouble() < 1500.01) {
                Text(text = "Renda baixa")
                Image(
                    painter = painterResource(id = R.mipmap.rendabaixa),
                    contentDescription = "Imagem que representa renda baixa",
                    modifier = modifier.size(250.dp)
                )
            } else if (rendaMensal.toDouble() < 5000.01) {
                Text(text = "Renda modesta")
                Image(
                    painter = painterResource(id = R.mipmap.rendamodesta),
                    contentDescription = "Imagem que representa renda modesta",
                    modifier = modifier.size(250.dp)
                )
            } else if (rendaMensal.toDouble() < 10000.01) {
                Text(text = "Renda confortável")
                Image(
                    painter = painterResource(id = R.mipmap.rendaconfortavel),
                    contentDescription = "Imagem que representa renda confortável",
                    modifier = modifier.size(250.dp)
                )
            } else {
                Text(text = "Renda alta")
                Image(
                    painter = painterResource(id = R.mipmap.rendaalta),
                    contentDescription = "Imagem que representa renda alta",
                    modifier = modifier.size(250.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    MyApplicationTheme {
        Form()
    }
}