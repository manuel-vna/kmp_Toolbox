package com.jumparoundcreations.toolbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.jumparoundcreations.toolbox.dashboard.DashboardScreen
import com.jumparoundcreations.toolbox.documentScanner.ui.ScanScreen
import com.jumparoundcreations.toolbox.running.RunningScreen

@Composable
fun Navigation() {

    val backStack = remember { mutableStateListOf<Any>(Routes.Dashboard) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider =
            entryProvider {
                entry<Routes.Dashboard> {
                    DashboardScreen(
                        onPaceCalculatorNavigation = { backStack.add(Routes.PaceCalculator) },
                        onDocumentScannerNavigation = { backStack.add(Routes.DocumentScanner) }
                    )
                }
                entry<Routes.PaceCalculator> {
                    RunningScreen()
                }
                entry<Routes.DocumentScanner> {
                    ScanScreen()
                }
            }
    )

}