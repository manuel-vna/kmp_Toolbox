package com.jumparoundcreations.toolbox.paceCalculator

import androidx.lifecycle.ViewModel
import com.jumparoundcreations.toolbox.di.dispose
import com.jumparoundcreations.toolbox.extensions.calculateFromDistanceAndTime
import com.jumparoundcreations.toolbox.extensions.calculateFromPace
import com.jumparoundcreations.toolbox.extensions.calculateFromSpeed
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
                changeMinute(action.newTimeMinute)
            }

            is PaceCalculatorIntents.ChangeTimeSecond -> {
                changeSecond(action.newTimeSecond)
            }

            is PaceCalculatorIntents.ChangePaceMinute -> {
                changePaceMinute(action.newPaceMinute)
            }

            is PaceCalculatorIntents.ChangePaceSecond -> {
                changePaceSecond(action.newPaceSecond)
            }

            is PaceCalculatorIntents.ChangeSpeed -> {
                changeSpeed(action.newSpeed)
            }
        }
    }

    fun changeDistance(newDistance: String) {
        _paceCalculatorStates.update { current ->
            current
                .copy(
                    distanceKm = newDistance,
                ).calculateFromDistanceAndTime()
        }
    }

    fun changeHour(newHour: String) {
        _paceCalculatorStates.update { current ->
            current
                .copy(
                    timeHour = newHour,
                ).calculateFromDistanceAndTime()
        }
    }

    fun changeMinute(newMinute: String) {
        _paceCalculatorStates.update { current ->
            current
                .copy(
                    timeMinute = newMinute,
                ).calculateFromDistanceAndTime()
        }
    }

    fun changeSecond(newSecond: String) {
        _paceCalculatorStates.update { current ->
            current
                .copy(
                    timeSecond = newSecond
                ).calculateFromDistanceAndTime()
        }
    }

    fun changePaceMinute(newPaceMinute: String) {
        _paceCalculatorStates.update { current ->
            current
                .copy(
                    paceMinute = newPaceMinute,
                ).calculateFromPace()
        }
    }

    fun changePaceSecond(newPaceSecond: String) {
        _paceCalculatorStates.update { current ->
            current
                .copy(
                    paceSecond = newPaceSecond
                ).calculateFromPace()
        }
    }

    fun changeSpeed(newSpeed: String) {
        _paceCalculatorStates.update { current ->
            current
                .copy(
                    speedKmPerHour = newSpeed,
                ).calculateFromSpeed()
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
