package com.jumparoundcreations.toolbox.paceCalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaceCalculatorScreen(
    viewModel: PaceCalculatorViewModel = koinViewModel()
) {
    val states by viewModel.paceCalculatorStates.collectAsState()

    Column {
        TextField(
            value = states.distanceKm,
            onValueChange = { }
        )
    }

}

@Preview
@Composable
fun PaceCalculatorScreenPreview() {
    PaceCalculatorScreen()
}
