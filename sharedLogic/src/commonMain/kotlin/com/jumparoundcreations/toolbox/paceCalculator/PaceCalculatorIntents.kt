package com.jumparoundcreations.toolbox.paceCalculator

sealed class PaceCalculatorIntents {
    data class ChangeDistance(
        val newDistance: Double
    ) : PaceCalculatorIntents()

    data class ChangeTimeHour(
        val newTimeHour: Double
    ) : PaceCalculatorIntents()

    data class ChangeTimeMinute(
        val newTimeMinute: Double
    ) : PaceCalculatorIntents()

    data class ChangeTimeSecond(
        val newTimeSecond: Double
    ) : PaceCalculatorIntents()

    data class ChangePace(
        val newPace: Double
    ) : PaceCalculatorIntents()

    data class ChangeSpeed(
        val newSpeed: Double
    ) : PaceCalculatorIntents()
}