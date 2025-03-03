package com.umb.app_calx.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.objecthunter.exp4j.ExpressionBuilder

data class CalculatorState(
    val input: String = "",
    val result: String = "",
    val history: List<String> = emptyList()
)

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorState())
    val uiState: StateFlow<CalculatorState> = _uiState

    private var isResultDisplayed = false

    fun onButtonClick(button: String) {
        when (button) {
            "=" -> calculateResult()
            "C" -> clearInput()
            else -> handleInput(button)
        }
    }

    private fun handleInput(value: String) {
        if (isResultDisplayed) {
            if (value in listOf("+", "-", "*", "/")) {
                // Si el usuario presiona un operador después del resultado, seguir operando
                _uiState.value = _uiState.value.copy(input = _uiState.value.result + value, result = "")
            } else {
                // Si presiona un número, iniciar nueva operación
                _uiState.value = _uiState.value.copy(input = value, result = "")
            }
            isResultDisplayed = false
        } else {
            // Continuar escribiendo normalmente
            _uiState.value = _uiState.value.copy(input = _uiState.value.input + value)
        }
    }

    private fun clearInput() {
        _uiState.value = _uiState.value.copy(input = "", result = "")  // Solo limpiar input y resultado, no el historial
        isResultDisplayed = false
    }

    private fun calculateResult() {
        try {
            val expression = ExpressionBuilder(_uiState.value.input).build()
            val resultValue = expression.evaluate()
            val resultText = if (_uiState.value.input.contains("/")) {
                resultValue.toString()
            } else {
                resultValue.toInt().toString()
            }

            val newHistory = _uiState.value.history + "${_uiState.value.input} = $resultText"
            _uiState.value = _uiState.value.copy(
                result = resultText,
                history = newHistory
            )
            isResultDisplayed = true
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(result = "Error")
            isResultDisplayed = true
        }
    }

    fun clearHistory() {
        _uiState.value = _uiState.value.copy(history = emptyList())
    }
}
