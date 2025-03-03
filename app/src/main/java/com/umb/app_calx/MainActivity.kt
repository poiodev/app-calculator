package com.umb.app_calx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umb.app_calx.ui.CalculatorScreen
import com.umb.app_calx.viewmodel.CalculatorViewModel
import com.umb.app_calx.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel: CalculatorViewModel = viewModel()
                CalculatorScreen(viewModel)
            }
        }
    }
}