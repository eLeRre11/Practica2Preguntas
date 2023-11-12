package Componentes

import Preguntas.Preguntas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica2aplicacionpreguntas.R
import kotlin.random.Random


@Composable
fun Juego(){
    var lista = ArrayList<Preguntas>()
    lista.add(Preguntas("¿Es este el verdadero Venoso?", R.drawable.venom, true))
    lista.add(Preguntas("¿Es Pepe el jugador más limpio de la historia?", R.drawable.pepe, true))
    lista.add(Preguntas("¿Es Hulk el vengador más fuerte?", R.drawable.hulk, false))
    lista.add(Preguntas("¿Vegetta es el mejor youtuber?", R.drawable.vegetta, true))
    lista.add(Preguntas("¿Es Hamilton el mejor piloto de F1?", R.drawable.lloron, false))

    var numeroRandom by remember { mutableStateOf(Random.nextInt(5)) }
    var retroalimentacionTexto by remember { mutableStateOf("") }
    var retroalimentacionColor by remember { mutableStateOf(Color.Transparent) }
    var respuestaUsuario by remember { mutableStateOf(false) }
    var indicePregunta by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto arriba, que es la pregunta, centrado
        Text(
            text = lista[numeroRandom].preguntas,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )

        // Imagen centrada y de mayor tamaño
        Image(
            painter = painterResource(id = lista[numeroRandom].imagen),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f).padding(16.dp)
        )

        // Espacio entre la imagen y los botones
        Spacer(modifier = Modifier.height(16.dp))

        // Texto de retroalimentación
        Text(
            text = retroalimentacionTexto,
            color = retroalimentacionColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp),
            fontSize = 20.sp
        )

        // Botones de true y false centrados
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (lista[numeroRandom].respuesta) {
                        retroalimentacionTexto = "Bien (de potra)"
                        retroalimentacionColor = Color.Green
                    } else {
                        retroalimentacionTexto = "Mentira, ESPABILA!"
                        retroalimentacionColor = Color.Red
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Si")
            }

            Button(
                onClick = {
                    if (!lista[numeroRandom].respuesta) {
                        retroalimentacionTexto = "Bien (de potra)"
                        retroalimentacionColor = Color.Green
                    } else {
                        retroalimentacionTexto = "Mentira, ESPABILA!"
                        retroalimentacionColor = Color.Red
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("No")
            }
        }

        // Botón de next, prev y random
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    retroalimentacionTexto = ""
                    retroalimentacionColor = Color.Transparent

                    if (numeroRandom > 0) {
                        numeroRandom--
                    } else {
                        // Reiniciar desde la ultima pregunta
                        numeroRandom = lista.size -1
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "")
                    Text(text = "PREV")
                }
            }
            Button(
                onClick = {
                    retroalimentacionTexto = ""
                    retroalimentacionColor = Color.Transparent
                    numeroRandom = Random.nextInt(5)
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "RANDOM")
                }
            }
            Button(
                onClick = {
                    retroalimentacionTexto = ""
                    retroalimentacionColor = Color.Transparent

                    if (numeroRandom < lista.size - 1) {
                        numeroRandom++
                    } else {
                        // Reiniciar desde la primera pregunta
                        numeroRandom = 0
                    }
                },
                modifier = Modifier.padding(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "")
                    Text(text = "NEXT")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewJuego(){
    Juego()
}

fun verificarColor(acierto: Boolean): Color {
    if (acierto) {
        return Color.Green
    } else {
        return Color.Red
    }
}

@Composable
fun BotonRespuesta(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = texto)
    }
}

fun verificarRespuesta(pregunta: Preguntas, respuestaUsuario: Boolean) {
    val esCorrecta = pregunta.respuesta == respuestaUsuario
    // Debes manejar las variables de retroalimentación en esta función o pasarlas como argumentos.
}

fun avanzarAPreguntaSiguiente(numeroRandom: Int, lista: List<Preguntas>): Int {
    if (numeroRandom < lista.size - 1) {
        return numeroRandom + 1
    } else {
        return numeroRandom
    }
}