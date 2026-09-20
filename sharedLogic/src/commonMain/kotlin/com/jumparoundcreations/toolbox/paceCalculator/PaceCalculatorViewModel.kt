package com.jumparoundcreations.toolbox.paceCalculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PaceCalculatorViewModel : ViewModel() {

    val _paceCalulatorStates = MutableStateFlow(PaceCalculatorStates())
    val paceCalculatorStates = _paceCalulatorStates.asStateFlow()

    fun onAction(action: PaceCalculatorIntents) {
        when (action) {
            is PaceCalculatorIntents.ChangeDistance -> {
                changeDistance(action.newDistance)
            }
        }
    }

    fun changeDistance(newDistance: Double) {
        _paceCalulatorStates.update { current ->
            current.copy(
                distance = newDistance
            )
        }
    }

}