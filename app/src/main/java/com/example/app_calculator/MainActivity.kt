package com.example.app_calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_calculator.ui.theme.AppcalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppcalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }

        // Prueba de la función sumar
        val resultado = sumar(5.0, 3.0) // Ejemplo de prueba con 5 + 3
        Log.d("Calculadora", "El resultado de la suma es: $resultado")
    }

    // 📌Función de suma dentro de la clase
    fun sumar(a: Double, b: Double): Double {
        return a + b
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var text1 by remember { mutableStateOf(TextFieldValue("")) }
    var text2 by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(text = "Calculadora", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = text1,
            onValueChange = { text1 = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = text2,
            onValueChange = { text2 = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val num1 = text1.text.toDoubleOrNull() ?: 0.0
                val num2 = text2.text.toDoubleOrNull() ?: 0.0
                result = (num1 + num2).toString()
            }
        ) {
            Text("Sumar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Resultado: $result", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    AppcalculatorTheme {
        CalculatorScreen()
    }
}
