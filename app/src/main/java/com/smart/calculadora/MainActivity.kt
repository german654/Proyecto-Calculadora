package com.smart.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smart.calculadora.ui.theme.CalculadoraTheme
import com.smart.calculadora.views.body.TicTacToeBody
import com.smart.calculadora.views.header.TicTacToeHeader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilitar el modo de pantalla completa
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header en la parte superior
        TicTacToeHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // Asegúrate de que la altura sea suficiente
        )

        // Body ocupando el resto del espacio disponible
        TicTacToeBody(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f) // Permite que el cuerpo ocupe el espacio restante
        )
    }
}

@Preview(widthDp = 470, heightDp = 1500)
@Composable
fun MainContentPreview() {
    CalculadoraTheme {
        MainContent()
    }
}