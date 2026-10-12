package com.jumparoundcreations.toolbox.running

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunningScreen(
    viewModel: RunningViewModel = koinViewModel()
) {
    val states by viewModel.runningStates.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Running Time Calculator",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            modifier = Modifier.padding(bottom = 24.dp),
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
                CustomTrailingIcon {
                    viewModel.onAction(
                        RunningIntents.ChangeDistance(
                            newDistance = ""
                        )
                    )
                }
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
                CustomTrailingIcon {
                    viewModel.onAction(
                        RunningIntents.ChangeTimeHour(
                            newTimeHour = ""
                        )
                    )
                }
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
                CustomTrailingIcon {
                    viewModel.onAction(
                        RunningIntents.ChangeTimeMinute(
                            newTimeMinute = ""
                        )
                    )
                }
            }
        )

        OutlinedTextField(
            modifier = Modifier.padding(bottom = 24.dp),
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
                CustomTrailingIcon {
                    viewModel.onAction(
                        RunningIntents.ChangeTimeSecond(
                            newTimeSecond = ""
                        )
                    )
                }
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
                CustomTrailingIcon {
                    viewModel.onAction(
                        RunningIntents.ChangePaceMinute(
                            newPaceMinute = ""
                        )
                    )
                }
            }
        )

        OutlinedTextField(
            modifier = Modifier.padding(bottom = 24.dp),
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
                CustomTrailingIcon {
                    viewModel.onAction(
                        RunningIntents.ChangePaceSecond(
                            newPaceSecond = ""
                        )
                    )
                }
            }
        )

        OutlinedTextField(
            modifier = Modifier.padding(bottom = 24.dp),
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
                CustomTrailingIcon {
                    viewModel.onAction(
                        RunningIntents.ChangeSpeed(
                            newSpeed = ""
                        )
                    )
                }
            }
        )

    }
}

@Composable
fun CustomTrailingIcon(onAction: () -> Unit) {
    IconButton(onClick = { onAction() }) {
        Icon(
            imageVector = Icons.Outlined.Cancel,
            contentDescription = "Clear speed",
        )
    }
}

@Preview
@Composable
fun RunningScreenPreview() {
    RunningScreen()
}
