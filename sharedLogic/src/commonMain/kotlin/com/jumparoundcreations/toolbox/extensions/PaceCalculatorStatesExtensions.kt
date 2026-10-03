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

fun PaceCalculatorStates.calculateFromPace(): PaceCalculatorStates {
    val distance = distanceKm.positiveDoubleOrNull()
    val paceSeconds = paceSecondsOrNull()

    if (distance == null || paceSeconds == null || paceSeconds == 0L) {
        return clearCalculatedTimeAndSpeed()
    }

    val calculatedTime = distance * paceSeconds
    if (!calculatedTime.isFinite() ||
        calculatedTime < 1.0 ||
        calculatedTime > Long.MAX_VALUE.toDouble()
    ) {
        return clearCalculatedTimeAndSpeed()
    }

    return withPopularDistances(paceSeconds)
        .withCalculatedTime(calculatedTime.roundToLong())
        .copy(
            speedKmPerHour = (3600.0 / paceSeconds).displayDecimal()
        )
}

fun PaceCalculatorStates.calculateFromSpeed(): PaceCalculatorStates {
    val distance = distanceKm.positiveDoubleOrNull()
    val speed = speedKmPerHour.positiveDoubleOrNull()

    if (distance == null || speed == null) {
        return clearCalculatedTimeAndPace()
    }

    val calculatedTime = distance * 3600.0 / speed
    val calculatedPace = 3600.0 / speed

    if (!calculatedTime.isFinite() ||
        calculatedTime < 1.0 ||
        calculatedTime > Long.MAX_VALUE.toDouble() ||
        !calculatedPace.isFinite() ||
        calculatedPace < 1.0 ||
        calculatedPace > Long.MAX_VALUE.toDouble()
    ) {
        return clearCalculatedTimeAndPace()
    }

    return withCalculatedTime(calculatedTime.roundToLong())
        .withCalculatedPace(calculatedPace.roundToLong())
}

internal fun PaceCalculatorStates.withPopularDistances(timeSeconds: Long?): PaceCalculatorStates {
    if (timeSeconds == null || timeSeconds <= 0) {
        return copy(
            popularOneKm = "",
            popularFiveKm = "",
            popularTenKm = "",
            popularTwentyOneKm = ""
        )
    }

    val timeFiveKm = timeSeconds * 5
    val timeTenKm = timeSeconds * 10
    val timeTwentyOneKm = (timeSeconds * 21.097).roundToLong()

    return copy(
        popularOneKm = formatToTimeString(timeSeconds),
        popularFiveKm = formatToTimeString(timeFiveKm),
        popularTenKm = formatToTimeString(timeTenKm),
        popularTwentyOneKm = formatToTimeString(timeTwentyOneKm)
    )
}

internal fun formatToTimeString(totalSeconds: Long?): String {
    if (totalSeconds == null || totalSeconds <= 0) return ""
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return if (hours > 0) {
        "$hours:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    } else {
        "$minutes:${seconds.toString().padStart(2, '0')}"
    }
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

private fun PaceCalculatorStates.paceSecondsOrNull(): Long? {
    val minutes = paceMinute.nonNegativeLongOrNull() ?: return null
    val seconds = paceSecond.nonNegativeLongOrNull() ?: return null

    if (seconds > 59 || minutes > Long.MAX_VALUE / 60) return null
    return minutes * 60 + seconds
}

private fun PaceCalculatorStates.clearCalculatedTimeAndSpeed(): PaceCalculatorStates =
    copy(
        timeHour = "",
        timeMinute = "",
        timeSecond = "",
        speedKmPerHour = ""
    )

private fun PaceCalculatorStates.clearCalculatedTimeAndPace(): PaceCalculatorStates =
    copy(
        timeHour = "",
        timeMinute = "",
        timeSecond = "",
        paceMinute = "",
        paceSecond = ""
    )