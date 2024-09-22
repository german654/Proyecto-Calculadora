package com.smart.calculadora.views.body

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.relay.compose.ColumnScopeInstanceImpl.weight
import com.smart.calculadora.R
import com.smart.calculadora.viewmodels.ResultadosViewModel
import com.smart.calculadora.views.juego.TicTacToeGameBoard

@Composable
fun TicTacToeBody(modifier: Modifier = Modifier) {
    // Inicializa el ResultadosViewModel
    val viewModel: ResultadosViewModel = viewModel()

    Column(modifier = modifier.fillMaxSize()) {
        // Sección de Jugadores
        PlayersSection {
            // Título del juego
            GameTitleContainer {
                TicTacToeTitle()
            }

            // Jugador 1 y Jugador 2
            PlayerSection(playerName = "Juan", playerLabel = "Nombre Jugador 1:")
            PlayerSection(playerName = "Pedro", playerLabel = "Nombre Jugador 2:")

            // Botones de Iniciar y Anular con acciones
            ControlButtons {
                ActionButton(
                    text = "Iniciar Partida",
                    viewModel = viewModel,
                    nombreJugador1 = "Jugador 1",
                    nombreJugador2 = "Jugador 2",
                    idPartida = 1,
                    esIniciarPartida = true
                )
                Spacer(modifier = Modifier.width(20.dp)) // Espacio entre los botones
                ActionButton(
                    text = "Anular Partida",
                    viewModel = viewModel,
                    nombreJugador1 = "Jugador 1",
                    nombreJugador2 = "Jugador 2",
                    idPartida = 1,
                    esIniciarPartida = false
                )
            }
        }

        // Tablero del juego
        GameBoard(modifier = Modifier.padding(top = 0.dp))

        // Sección de Puntajes
        ScoreSection {
            // Estados del Juego
            Scoreboard {
                // Turno del Jugador
                TurnBox("J1: Juan (x)")
                Spacer(modifier = Modifier.width(5.dp))
                // Ganador del Juego: J1 o J2 o empate
                WinnerBox("Empate")
            }
            // Tabla de puntajes
            // Titulo de la tabla de puntajes
            ScoreTable { ScoreTableTitle() }

            // Tabla de puntajes del partido
            // En qui deben de estar los resultados del partido
            MatchScoreContainer {
                MatchInfo(
                    matchTitle = "Partido 1",
                    matchResult = "Ganador: Empate",
                    labelScore = "P:",
                    labelStatus = "E:",
                    score = 0,
                    status = "T"
                )
            }
        }
    }
}


@Preview(widthDp = 430, heightDp = 1000)
@Composable
fun TicTacToeBodyPreview() {
    MaterialTheme {
        TicTacToeBody()
    }
}

// Sección de jugadores
@Composable
fun PlayersSection(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        content()
    }
}

// Sección de Jugadores
@Composable
fun PlayerSection(playerName: String, playerLabel: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(0.dp), // Agregar padding
        horizontalAlignment = Alignment.CenterHorizontally // Centrar contenido horizontalmente
    ) {
        // Label del jugador
        PlayerLabel(playerLabel)

        // Rectángulo con el nombre del jugador
        PlayerInfo(name = playerName)
    }
}

@Composable
fun PlayerInfo(name: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .border(
                1.dp,
                Color.Black,
                RoundedCornerShape(8.dp)
            ) // Borde negro y esquinas redondeadas
            .width(300.dp) // Ancho ajustado
            .height(50.dp) // Altura ajustada
            .padding(16.dp) // Padding interno
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = inter,
            lineHeight = 1.21.em,
            textAlign = TextAlign.Center // Centrar el texto
        )
    }
}

@Composable
fun PlayerLabel(label: String) {
    Box(
        modifier = Modifier
            .padding(bottom = 4.dp)
            .background(Color.Transparent) // Sin borde, pero se mantiene el área de fondo
            .width(300.dp) // Alinear con el rectángulo del nombre
            .height(30.dp) // Altura ajustada
            .padding(vertical = 8.dp, horizontal = 16.dp) // Padding interno
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontFamily = laila,
            fontWeight = FontWeight.Light,
            lineHeight = 1.55.em,
            textAlign = TextAlign.Center // Centrar el texto
        )
    }
}

// Título del juego
@Composable
fun TicTacToeTitle(modifier: Modifier = Modifier) {
    Text(
        text = "3 en Raya",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold, // Negrita
        fontFamily = inter,
        textAlign = TextAlign.Center, // Centramos el texto
        modifier = modifier.fillMaxWidth() // Aseguramos que ocupe todo el ancho para centrar el texto
    )
}

// Contenedor del título
@Composable
fun GameTitleContainer(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Ajustamos padding para más flexibilidad
            .height(48.dp), // Un poco más alto para mejor visualización
        contentAlignment = Alignment.Center // Centramos el contenido dentro del contenedor
    ) {
        content()
    }
}

// Botones de acción
@Composable
fun ControlButtons(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun ActionButton(
    text: String,
    viewModel: ResultadosViewModel,
    nombreJugador1: String,
    nombreJugador2: String,
    idPartida: Int,
    esIniciarPartida: Boolean // Indica si es el botón de "Iniciar" o "Anular"
) {
    val nombrePartida = "Partida $idPartida" // Generar nombre de la partida según el partido

    Box(
        modifier = Modifier
            .background(Color(0xFF005CC9), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 28.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                if (esIniciarPartida) {
                    viewModel.iniciarPartida(nombreJugador1, nombreJugador2, nombrePartida, idPartida)
                } else {
                    viewModel.anularPartida(idPartida)
                }
            }
    ) {
        Text(
            text = text,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = inter,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


// Tablero del juego
@Composable
fun GameBoard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(460.dp)
    ) {
        TicTacToeGameBoard(modifier = Modifier.align(Alignment.Center))
    }
}

// Sección de puntajes
@Composable
fun ScoreSection(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 5.dp)
    ) {
        content()
    }
}

// Estados del Juego
@Composable
fun Scoreboard(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 30.dp), // Reducido el padding horizontal
        horizontalArrangement = Arrangement.Center, // Centramos el contenido
        verticalAlignment = Alignment.CenterVertically // Alineamos verticalmente
    ) {
        content()
    }
}


// Modificar el componente TurnBox para el turno del jugador
@Composable
fun TurnBox(playerName: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .padding(3.dp)
        ) {
            CircleLabel("T:")
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = playerName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = inter,
                color = Color.Black
            )
        }
    }
}

// Modificar el componente WinnerBox para el ganador del juego
@Composable
fun WinnerBox(winner: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .padding(3.dp)
        ) {
            CircleLabel("G:")
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = winner,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = inter,
                color = Color.Black
            )
        }
    }
}

// Componente para la letra dentro del círculo (T, G, P, E)
@Composable
fun CircleLabel(letter: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Gray, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Título de la tabla de puntajes centrado
@Composable
fun ScoreTableTitle() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center // Centrar el texto
    ) {
        Text(
            text = "TABLA DE PUNTAJES",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

// Contenedor de la tabla de puntajes
@Composable
fun ScoreTable(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        content()
    }
}

// Contenedor de resultados de los partidos
@Composable
fun MatchScoreContainer(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        content()
    }
}

// Componente que muestra el número del partido y el ganador en la izquierda
@Composable
fun MatchLeftInfo(matchTitle: String, matchResult: String) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = matchTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = inter
        )
        Text(
            text = matchResult,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF041BA3),
            fontFamily = inter
        )
    }
}

// Componente que muestra el puntaje y el estado del juego en la derecha
@Composable
fun MatchRightInfo(labelScore: String, labelStatus: String, score: Int, status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center // Ajustamos el espacio entre los elementos
    ) {
        // Caja para el puntaje
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color(0xFFE0E0E0))
                    .padding(3.dp) // Espaciado interno ajustado para dar mejor presentación
            ) {
                CircleLabel(labelScore)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = score.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = inter
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp)) // Reducimos el espacio entre las cajas

        // Caja para el estado del juego
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color(0xFFE0E0E0))
                    .padding(3.dp) // Espaciado interno ajustado
            ) {
                CircleLabel(labelStatus)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = status,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = inter
                )
            }
        }
    }
}


// Contenedor de MatchInfo que combina ambos componentes
@Composable
fun MatchInfo(
    matchTitle: String,
    matchResult: String,
    labelScore: String,
    labelStatus: String,
    score: Int,
    status: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        MatchLeftInfo(matchTitle, matchResult) // Información del partido y ganador
        Spacer(modifier = Modifier.width(20.dp))
        MatchRightInfo(labelScore, labelStatus, score, status)  // Puntaje y estado del juego
    }
}
