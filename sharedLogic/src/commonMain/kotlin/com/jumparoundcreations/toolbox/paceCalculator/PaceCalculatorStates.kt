package com.jumparoundcreations.toolbox.paceCalculator

data class PaceCalculatorStates(
    val distanceKm: String = "10",
    val timeHour: String = "",
    val timeMinute: String = "",
    val timeSecond: String = "",
    val paceMinute: String = "",
    val paceSecond: String = "",
    val speedKmPerHour: String = "",
    val popularOneKm: String = "",
    val popularFiveKm: String = "",
    val popularTenKm: String = "",
    val popularTwentyOneKm: String = "",
)
