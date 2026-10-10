package com.jumparoundcreations.toolbox.paceCalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaceCalculatorScreen(
    viewModel: PaceCalculatorViewModel = koinViewModel()
) {
    val states by viewModel.paceCalculatorStates.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(

            label = { Text("Distance") },
            value = states.distanceKm,
            onValueChange = {
                viewModel.onAction(
                    PaceCalculatorIntents.ChangeDistance(
                        newDistance = it
                    )
                )
            },
            suffix = { Text("km") },
            trailingIcon = {
                Icon(
                    Icons.Outlined.Cancel,
                    null
                )
            }
        )
    }

}

@Preview
@Composable
fun PaceCalculatorScreenPreview() {
    PaceCalculatorScreen()
}
