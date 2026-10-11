package com.jumparoundcreations.toolbox.running

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
fun RunningScreen(
    viewModel: RunningViewModel = koinViewModel()
) {
    val states by viewModel.runningStates.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = { Text("Distance") },
            value = states.distance,
            onValueChange = { newValue ->
                viewModel.onAction(
                    RunningIntents.ChangeDistance(
                        newDistance = newValue
                    )
                )
            },
            suffix = { Text("km") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Clear distance"
                )
            }
        )

        OutlinedTextField(
            label = { Text("Time Hour") },
            value = states.time.hour,
            onValueChange = { newValue ->
                viewModel.onAction(
                    RunningIntents.ChangeTimeHour(
                        newTimeHour = newValue
                    )
                )
            },
            suffix = { Text("h") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Clear hour"
                )
            }
        )

        OutlinedTextField(
            label = { Text("Time Minute") },
            value = states.time.minute,
            onValueChange = { newValue ->
                viewModel.onAction(
                    RunningIntents.ChangeTimeMinute(
                        newTimeMinute = newValue
                    )
                )
            },
            suffix = { Text("m") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Clear minute"
                )
            }
        )

        OutlinedTextField(
            label = { Text("Time Second") },
            value = states.time.second,
            onValueChange = { newValue ->
                viewModel.onAction(
                    RunningIntents.ChangeTimeSecond(
                        newTimeSecond = newValue
                    )
                )
            },
            suffix = { Text("s") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Clear second"
                )
            }
        )

        OutlinedTextField(
            label = { Text("Pace Minute") },
            value = states.pace.minute,
            onValueChange = { newValue ->
                viewModel.onAction(
                    RunningIntents.ChangePaceMinute(
                        newPaceMinute = newValue
                    )
                )
            },
            suffix = { Text("m") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Clear pace minute"
                )
            }
        )

        OutlinedTextField(
            label = { Text("Pace Second") },
            value = states.pace.second,
            onValueChange = { newValue ->
                viewModel.onAction(
                    RunningIntents.ChangePaceSecond(
                        newPaceSecond = newValue
                    )
                )
            },
            suffix = { Text("s") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Clear pace second"
                )
            }
        )

        OutlinedTextField(
            label = { Text("Speed") },
            value = states.speed,
            onValueChange = { newValue ->
                viewModel.onAction(
                    RunningIntents.ChangeSpeed(
                        newSpeed = newValue
                    )
                )
            },
            suffix = { Text("km/h") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Clear speed"
                )
            }
        )

    }
}

@Preview
@Composable
fun RunningScreenPreview() {
    RunningScreen()
}
