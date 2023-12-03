package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Llama a la función principal de tu aplicación
            LemonadeApp()
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    // Representa la interfaz de usuario con Compose
    var currentScreen by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        // Encabezado con fondo amarillo
        LemonadeHeader()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            LemonStep(
                R.string.lemon_tree,
                R.drawable.lemon_tree,
                currentScreen == 0
            ) {
                currentScreen = (currentScreen + 1) % 4
            }
            LemonStep(
                R.string.lemon,
                R.drawable.lemon_squeeze,
                currentScreen == 1,
                requiredPresses = (2..4).random()
            ) {
                currentScreen = (currentScreen + 1) % 4
            }
            LemonStep(
                R.string.Glass_of_lemonade,
                R.drawable.lemon_drink,
                currentScreen == 2
            ) {
                currentScreen = (currentScreen + 1) % 4
            }
            LemonStep(
                R.string.Empty_glass,
                R.drawable.lemon_restart,
                currentScreen == 3
            ) {
                currentScreen = (currentScreen + 1) % 4
            }
        }
    }
}

@Composable
fun LemonadeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()  // Ocupa el ancho completo de la pantalla
            .height(60.dp)
            .background(Color.Yellow) // Usa el color amarillo definido
    ) {
        Text(
            text = "Lemonade",
            fontSize = 24.sp,
            color = Color.Black, // Puedes ajustar el color del texto según sea necesario
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}


@Composable
fun LemonStep(textRes: Int, imageRes: Int, isVisible: Boolean, requiredPresses: Int = 0, onStepComplete: () -> Unit) {
    if (isVisible) {
        var presses by remember { mutableStateOf(0) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(Color(176, 224, 230)) // Puedes usar el color azul claro definido
                    .clickable {
                        // Incrementa el contador de presiones al hacer clic
                        presses++

                        // Verifica si se alcanzó la cantidad requerida de presiones
                        if (presses >= requiredPresses) {
                            // Llama a la función de completado cuando se alcanza el objetivo
                            onStepComplete()
                        }
                    }
                    .border(
                        width = 2.dp,
                        color = Color(105, 205, 216),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = textRes),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

