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
            is PaceCalculatorIntents.ChangeTimeHour -> {
                changeHour(action.newTimeHour)
            }

            is PaceCalculatorIntents.ChangeTimeMinute -> {
                changeHour(action.newTimeMinute)
            }

            is PaceCalculatorIntents.ChangeTimeSecond -> {
                changeHour(action.newTimeSecond)
            }

            is PaceCalculatorIntents.ChangePace -> {
                changePace(action.newPace)
            }

            is PaceCalculatorIntents.ChangeSpeed -> {
                changeSpeed(action.newSpeed)
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

    fun changeHour(newHour: Double) {
        _paceCalculatorStates.update { current ->
            current.copy(
                timeHour = newHour,
            )
        }
    }

    fun changeMinute(newMinute: Double) {
        _paceCalculatorStates.update { current ->
            current.copy(
                timeHour = newMinute,
            )
        }
    }

    fun changeSecond(newSecond: Double) {
        _paceCalculatorStates.update { current ->
            current.copy(
                timeSecond = newSecond,
            )
        }
    }

    fun changePace(newPace: Double) {
        _paceCalculatorStates.update { current ->
            current.copy(
                pace = newPace,
            )
        }
    }

    fun changeSpeed(newSpeed: Double) {
        _paceCalculatorStates.update { current ->
            current.copy(
                speed = newSpeed,
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
