package com.smart.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.smart.calculadora.models.Resultados
import com.smart.calculadora.models.room.ResultadosDatabaseDao
import com.smart.calculadora.room.ResultadosDatabase
import com.smart.calculadora.ui.theme.CalculadoraTheme
import com.smart.calculadora.viewmodels.ResultadosViewModel
import com.smart.calculadora.views.body.TicTacToeBody
import com.smart.calculadora.views.header.TicTacToeHeader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura el contenido de la pantalla
        setContent {
            // Estado que controla el modo oscuro
            var isDarkTheme by remember { mutableStateOf(false) }

            CalculadoraTheme {
                // Superficie que ocupa toda la pantalla
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Inicializa la base de datos Room
                    val database = Room.databaseBuilder(
                        this,
                        ResultadosDatabase::class.java,
                        "db_resultados"
                    ).build()

                    // Obtiene el DAO (Data Access Object) para interactuar con la tabla "resultados"
                    val dao = database.resultadosDao()

                    // Crea el ViewModel que gestionará los datos de la partida
                    val viewModel = ResultadosViewModel(dao)

                    // Muestra el contenido principal del juego
                    MainContent(modifier = Modifier, viewModel = viewModel, onThemeChange = {
                        isDarkTheme = !isDarkTheme
                    })
                }
            }
        }
    }

    @Composable
    fun MainContent(
        modifier: Modifier = Modifier, // Modificador
        viewModel: ResultadosViewModel, //Connexion con el view model
        onThemeChange: () -> Unit // cambio de tema
    ) {
        // Estado de desplazamiento
        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxSize() // Ocupa toda la pantalla
                .padding(8.dp)
                .verticalScroll(scrollState), // Padding de 16dp en los bordes
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre elementos
        ) {
            // Sección superior con el header del juego
            TicTacToeHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), // Altura fija de 50dp
                viewModel = viewModel,
                onThemeChange = onThemeChange
            )

            // Sección principal del juego
            TicTacToeBody(
                modifier = Modifier
                    .fillMaxWidth() // Ocupa el espacio restante
                    .weight(1f), // Ajusta el tamaño para ocupar el resto de la pantalla
                viewModel = viewModel
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainContentPreview() {
        // Simulación de un ViewModel vacío para evitar errores en el preview
        val fakeDao = object : ResultadosDatabaseDao {
            override fun obtenerResultados(): Flow<List<Resultados>> {
                TODO("Not yet implemented")
            }

            override fun obtenerResultado(id: Int): Flow<Resultados> {
                TODO("Not yet implemented")
            }

            override suspend fun insertarPartida(resultados: Resultados) {
                TODO("Not yet implemented")
            }

            override suspend fun actualizarResultado(resultado: Resultados) {
                TODO("Not yet implemented")
            }

            override suspend fun borrarResultado(resultado: Resultados) {
                TODO("Not yet implemented")
            }

            override suspend fun iniciarPartida(id: Int) {}
            override suspend fun anularPartida(id: Int) {
                TODO("Not yet implemented")
            }

            override suspend fun finalizarPartida(id: Int, ganador: String, punto: Int) {
                TODO("Not yet implemented")
            }

            override fun obtenerEstadoPartida(partidaId: Int): Flow<String> {
                return flowOf("J") // Estado ficticio
            }
        }

        val fakeViewModel = ResultadosViewModel(fakeDao)

        // Proporcionar una función lambda vacía para 'onThemeChange' en el preview
        CalculadoraTheme {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight() // Ajusta la altura según el contenido
            ) {
                MainContent(viewModel = fakeViewModel, onThemeChange = {})
            }
        }
    }
}