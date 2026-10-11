package com.jumparoundcreations.toolbox.running

import androidx.lifecycle.ViewModel
import com.jumparoundcreations.toolbox.di.dispose
import com.jumparoundcreations.toolbox.extensions.calculateFromDistanceAndTime
import com.jumparoundcreations.toolbox.extensions.calculateFromPace
import com.jumparoundcreations.toolbox.extensions.calculateFromSpeed
import com.jumparoundcreations.toolbox.extensions.distanceAndSpeedFilter
import com.jumparoundcreations.toolbox.extensions.hourInputFilter
import com.jumparoundcreations.toolbox.extensions.minuteSecondInputFilter
import com.jumparoundcreations.toolbox.running.states.RunningStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RunningViewModel : ViewModel() {
    private val _runningStates = MutableStateFlow(RunningStates())
    val runningStates = _runningStates.asStateFlow()

    fun onAction(action: RunningIntents) {
        when (action) {
            is RunningIntents.ChangeDistance -> {
                changeDistance(action.newDistance)
            }

            is RunningIntents.ChangeTimeHour -> {
                changeTimeHour(action.newTimeHour)
            }

            is RunningIntents.ChangeTimeMinute -> {
                changeTimeMinute(action.newTimeMinute)
            }

            is RunningIntents.ChangeTimeSecond -> {
                changeTimeSecond(action.newTimeSecond)
            }

            is RunningIntents.ChangePaceMinute -> {
                changePaceMinute(action.newPaceMinute)
            }

            is RunningIntents.ChangePaceSecond -> {
                changePaceSecond(action.newPaceSecond)
            }

            is RunningIntents.ChangeSpeed -> {
                changeSpeed(action.newSpeed)
            }
        }
    }

    fun changeDistance(newDistance: String) {
        if (newDistance.distanceAndSpeedFilter()) {
            _runningStates.update { current ->
                current.copy(
                    distance = newDistance.replace(",", ".")
                ).calculateFromDistanceAndTime()
            }
        }
    }

    fun changeTimeHour(newHour: String) {
        if (newHour.hourInputFilter()) {
            _runningStates.update { current ->
                current.copy(
                    time = current.time.copy(hour = newHour)
                ).calculateFromDistanceAndTime()
            }
        }
    }

    fun changeTimeMinute(newMinute: String) {
        if (newMinute.minuteSecondInputFilter()) {
            _runningStates.update { current ->
                current.copy(
                    time = current.time.copy(minute = newMinute)
                ).calculateFromDistanceAndTime()
            }
        }
    }

    fun changeTimeSecond(newSecond: String) {
        if (newSecond.minuteSecondInputFilter()) {
            _runningStates.update { current ->
                current.copy(
                    time = current.time.copy(second = newSecond)
                ).calculateFromDistanceAndTime()
            }
        }
    }

    fun changePaceMinute(newPaceMinute: String) {
        if (newPaceMinute.minuteSecondInputFilter()) {
            _runningStates.update { current ->
                current.copy(
                    pace = current.pace.copy(minute = newPaceMinute)
                ).calculateFromPace()
            }
        }
    }

    fun changePaceSecond(newPaceSecond: String) {
        if (newPaceSecond.minuteSecondInputFilter()) {
            _runningStates.update { current ->
                current.copy(
                    pace = current.pace.copy(second = newPaceSecond)
                ).calculateFromPace()
            }
        }
    }

    fun changeSpeed(newSpeed: String) {
        if (newSpeed.distanceAndSpeedFilter()) {
            _runningStates.update { current ->
                current.copy(
                    speed = newSpeed.replace(",", ".")
                ).calculateFromSpeed()
            }
        }
    }

    fun onDispose() {
        this.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        println("RunningViewModel onCleared")
    }
}
