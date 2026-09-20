package com.jumparoundcreations.toolbox.paceCalculator

sealed class PaceCalculatorIntents {
    data class ChangeDistance(
        val newDistance: Double
    ) : PaceCalculatorIntents()
}