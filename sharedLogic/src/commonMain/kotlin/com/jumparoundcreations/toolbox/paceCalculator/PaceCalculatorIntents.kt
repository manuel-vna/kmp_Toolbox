package com.jumparoundcreations.toolbox.paceCalculator

sealed class PaceCalculatorIntents {
    data class ChangeDistance(
        val newDistance: String
    ) : PaceCalculatorIntents()

    data class ChangeTimeHour(
        val newTimeHour: String
    ) : PaceCalculatorIntents()

    data class ChangeTimeMinute(
        val newTimeMinute: String
    ) : PaceCalculatorIntents()

    data class ChangeTimeSecond(
        val newTimeSecond: String
    ) : PaceCalculatorIntents()

    data class ChangePaceMinute(
        val newPaceMinute: String
    ) : PaceCalculatorIntents()

    data class ChangePaceSecond(
        val newPaceSecond: String
    ) : PaceCalculatorIntents()

    data class ChangeSpeed(
        val newSpeed: String
    ) : PaceCalculatorIntents()
}