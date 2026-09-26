package com.jumparoundcreations.toolbox.paceCalculator

data class PaceCalculatorStates(
    val distance: Double = 0.0,
    val timeHour: Double = 0.0,
    val timeMinute: Double = 0.0,
    val timeSecond: Double = 0.0,
    val timeTotalInMinutes: Double = 0.0,
    val pace: Double = 0.0,
    val speed: Double = 0.0
)
