package com.jumparoundcreations.toolbox.extensions

import com.jumparoundcreations.toolbox.paceCalculator.PaceCalculatorStates

import kotlin.math.roundToLong


fun PaceCalculatorStates.calculateFromDistanceAndTime(): PaceCalculatorStates {
    val distance = distanceKm.positiveDoubleOrNull()
    val timeSeconds = timeSecondsOrNull()

    if (distance == null || timeSeconds == null || timeSeconds == 0L) {
        return copy(paceMinute = "", paceSecond = "", speedKmPerHour = "")
    }

    val paceSeconds = (timeSeconds / distance).roundToLong()
    val speed = distance * 3600.0 / timeSeconds

    if (paceSeconds < 1 || !speed.isFinite()) {
        return copy(paceMinute = "", paceSecond = "", speedKmPerHour = "")
    }

    return withPopularDistances(paceSeconds)
        .withCalculatedPace(paceSeconds)
        .copy(
            speedKmPerHour = speed.displayDecimal()
        )
}

private fun PaceCalculatorStates.withPopularDistances(timeSeconds: Long?): PaceCalculatorStates {

    val timeSecondsFiveTimes = timeSeconds?.times(5)
    val minutesFiveKm = (timeSecondsFiveTimes?.div(60))?.toInt()
    val secondsFiveLeft = minutesFiveKm?.times(60) ?: 0
    val secondsFiveKm = (timeSecondsFiveTimes?.toInt()?.minus(secondsFiveLeft))

    val timeSecondsTenTimes = timeSeconds?.times(10)
    val minutesTenKm = (timeSecondsTenTimes?.div(60))?.toInt()
    val secondsTenLeft = minutesTenKm?.times(60) ?: 0
    val secondsTenKm = (timeSecondsTenTimes?.toInt()?.minus(secondsTenLeft))

    return copy(
        popularFiveKm = "$minutesFiveKm:$secondsFiveKm",
        popularTenKm = "$minutesTenKm:$secondsTenKm",
    )

}

private fun PaceCalculatorStates.timeSecondsOrNull(): Long? {
    val hours = timeHour.nonNegativeLongOrNull() ?: return null
    val minutes = timeMinute.nonNegativeLongOrNull() ?: return null
    val seconds = timeSecond.nonNegativeLongOrNull() ?: return null

    if (minutes > 59 || seconds > 59) return null

    // Avoid overflow when converting a very large typed hour value.
    if (hours > (Long.MAX_VALUE - 3599) / 3600) return null

    return hours * 3600 + minutes * 60 + seconds
}

private fun PaceCalculatorStates.withCalculatedPace(secondsPerKm: Long): PaceCalculatorStates =
    copy(
        paceMinute = (secondsPerKm / 60).toString(),
        paceSecond = (secondsPerKm % 60).toString().padStart(2, '0')
    )

private fun PaceCalculatorStates.withCalculatedTime(seconds: Long): PaceCalculatorStates =
    copy(
        timeHour = (seconds / 3600).toString(),
        timeMinute = ((seconds % 3600) / 60).toString().padStart(2, '0'),
        timeSecond = (seconds % 60).toString().padStart(2, '0')
    )