package com.smart.calculadora.views.juego

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.relay.compose.BoxScopeInstanceImpl.align

@Composable
fun TicTacToeGameBoard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent
) {
    Box(
        modifier = modifier
            .fillMaxSize()  // Asegura que el contenedor ocupe toda la pantalla
            .padding(28.dp),
        contentAlignment = Alignment.Center // Centra el tablero en la pantalla
    ) {
        Column(
            modifier = Modifier
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Filas del tablero
            TicTacToeRow {
                TicTacToeCell(symbol = "O")
                TicTacToeCell(symbol = "X")
                TicTacToeCell(symbol = "O")
            }
            TicTacToeRow {
                TicTacToeCell(symbol = "X")
                TicTacToeCell(symbol = "O")
                TicTacToeCell(symbol = "X")
            }
            TicTacToeRow {
                TicTacToeCell(symbol = "O")
                TicTacToeCell(symbol = "X")
                TicTacToeCell(symbol = "O")
            }
        }
    }
}

@Composable
fun TicTacToeRow(
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        content = content
    )
}

@Composable
fun TicTacToeCell(symbol: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(110.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )  // Borde negro alrededor de la celda
            .background(Color(0xFF0460A3)) // Color de fondo
            .align(Alignment.Center)
    ) {
        Text(
            text = symbol,
            fontSize = 64.sp,  // Ajuste del tamaño de la fuente
            fontWeight = FontWeight.Bold,
            color = when (symbol) {
                "O" -> Color.White
                "X" -> Color(0xFFBB535B)
                else -> Color.White
            },
            modifier = Modifier.align(Alignment.Center) // Centra el texto dentro de la celda
        )
    }
}

@Preview(widthDp = 430, heightDp = 430)
@Composable
fun TicTacToeGameBoardPreview() {
    MaterialTheme {
        TicTacToeGameBoard()
    }
}
