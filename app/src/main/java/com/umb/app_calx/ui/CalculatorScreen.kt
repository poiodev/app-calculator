package com.umb.app_calx.ui

import ButtonGrid
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.umb.app_calx.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {
    val state by viewModel.uiState.collectAsState()
    var showHistory by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Display(state.input, state.result)

        ButtonGrid(
            state.input,
            onButtonClick = { button -> viewModel.onButtonClick(button) }
        )

        // Menú desplegable para el historial
        Button(onClick = { showHistory = !showHistory }) {
            Text(text = if (showHistory) "Ocultar Historial" else "Mostrar Historial")
        }

        if (showHistory) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            ) {
                HistoryDisplay(state.history)
            }
        }
    }
}