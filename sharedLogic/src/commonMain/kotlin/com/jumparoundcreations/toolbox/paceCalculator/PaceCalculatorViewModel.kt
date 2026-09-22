package com.jumparoundcreations.toolbox.paceCalculator

import androidx.lifecycle.ViewModel
import com.jumparoundcreations.toolbox.di.dispose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PaceCalculatorViewModel : ViewModel() {

    private val _paceCalculatorStates = MutableStateFlow(PaceCalculatorStates())
    val paceCalculatorStates = _paceCalculatorStates.asStateFlow()

    fun onAction(action: PaceCalculatorIntents) {
        when (action) {
            is PaceCalculatorIntents.ChangeDistance -> {
                changeDistance(action.newDistance)
            }
        }
    }

    fun changeDistance(newDistance: Double) {
        _paceCalculatorStates.update { current ->
            current.copy(
                distance = newDistance,
            )
        }
    }

    fun onDispose() {
        this.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        println("PaceCalculatorViewModel onCleared")
    }
}
