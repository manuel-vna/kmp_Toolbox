package com.jumparoundcreations.toolbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.jumparoundcreations.toolbox.dashboard.DashboardScreen
import com.jumparoundcreations.toolbox.paceCalculator.PaceCalculatorScreen

@Composable
fun Navigation() {

    val backStack = remember { mutableStateListOf<Any>(Routes.Dashboard) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider =
            entryProvider {
                entry<Routes.Dashboard> {
                    DashboardScreen()
                }
                entry<Routes.PaceCalculator> {
                    PaceCalculatorScreen()
                }
            }
    )

}