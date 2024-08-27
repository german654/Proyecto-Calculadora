package com.smart.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smart.calculadora.ui.theme.CalculadoraTheme
import kotlin.math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // pantalla completa con bordes ajustados UI
        setContent { // CONFIGURACION
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    var inputNumber by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column( // lINEAR LAYOUT
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Calculadora Avanzada",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = inputNumber,
            onValueChange = { inputNumber = it },
            label = { Text("Ingrese un número") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botones para realizar las operaciones
        Button(onClick = {
            result = calculatePower(inputNumber)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular Potencia (x^2)")
        }

        Button(onClick = {
            result = calculateSquareRoot(inputNumber)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular Raíz Cuadrada (√x)")
        }

        Button(onClick = {
            result = calculateReciprocal(inputNumber)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular 1/x")
        }

        Button(onClick = {
            result = calculatePi()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular Pi (π)")
        }

        Button(onClick = {
            result = calculateModulus(inputNumber)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular Módulo (|x|)")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Resultado: $result", fontSize = 20.sp)
    }
}

fun calculatePower(input: String): String {
    val number = input.toDoubleOrNull() ?: return "Entrada inválida"
    return (number.pow(2)).toString()
}

fun calculateSquareRoot(input: String): String {
    val number = input.toDoubleOrNull() ?: return "Entrada inválida"
    return if (number >= 0) sqrt(number).toString() else "No se puede calcular la raíz cuadrada de un número negativo"
}

fun calculateReciprocal(input: String): String {
    val number = input.toDoubleOrNull() ?: return "Entrada inválida"
    return if (number != 0.0) (1 / number).toString() else "No se puede dividir entre cero"
}

fun calculatePi(): String {
    return PI.toString()
}

fun calculateModulus(input: String): String {
    val number = input.toDoubleOrNull() ?: return "Entrada inválida"
    return abs(number).toString()
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalculadoraTheme {
        CalculatorApp()
    }
}
