package com.jumparoundcreations.toolbox.extensions

import com.jumparoundcreations.toolbox.running.states.RunningStates
import kotlin.math.roundToLong

fun RunningStates.calculateFromDistanceAndTime(): RunningStates {
    val distance = distance.positiveDoubleOrNull()
    val timeSeconds = timeSecondsOrNull()

    if (distance == null || timeSeconds == null || timeSeconds == 0L) {
        return copy(
            pace = pace.copy(minute = "", second = ""),
            speed = ""
        )
    }

    val paceSeconds = (timeSeconds / distance).roundToLong()
    val speed = distance * 3600.0 / timeSeconds

    if (paceSeconds < 1 || !speed.isFinite()) {
        return copy(
            pace = pace.copy(minute = "", second = ""),
            speed = ""
        )
    }

    return withPopularDistances(paceSeconds)
        .withCalculatedPace(paceSeconds)
        .copy(
            speed = speed.displayDecimal()
        )
}

fun RunningStates.calculateFromPace(): RunningStates {
    val distance = distance.positiveDoubleOrNull()
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
            speed = (3600.0 / paceSeconds).displayDecimal()
        )
}

fun RunningStates.calculateFromSpeed(): RunningStates {
    val distance = distance.positiveDoubleOrNull()
    val speed = speed.positiveDoubleOrNull()

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

internal fun RunningStates.withPopularDistances(timeSeconds: Long?): RunningStates {
    if (timeSeconds == null || timeSeconds <= 0) {
        return copy(
            popularDistances = popularDistances.copy(
                nullPointFourKm = "",
                oneKm = "",
                fiveKm = "",
                tenKm = "",
                twentyOneKm = ""
            ),
        )
    }

    val timeNullPointFourKm = (timeSeconds * 0.4).roundToLong()
    val timeFiveKm = timeSeconds * 5
    val timeTenKm = timeSeconds * 10
    val timeTwentyOneKm = (timeSeconds * 21.097).roundToLong()

    return copy(
        popularDistances = popularDistances.copy(
            nullPointFourKm = formatToTimeString(timeNullPointFourKm),
            oneKm = formatToTimeString(timeSeconds),
            fiveKm = formatToTimeString(timeFiveKm),
            tenKm = formatToTimeString(timeTenKm),
            twentyOneKm = formatToTimeString(timeTwentyOneKm)
        ),
    )

}

internal fun formatToTimeString2(totalSeconds: Long?): String {
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

private fun RunningStates.timeSecondsOrNull(): Long? {
    val hours = time.hour.nonNegativeLongOrNull() ?: return null
    val minutes = time.minute.nonNegativeLongOrNull() ?: return null
    val seconds = time.second.nonNegativeLongOrNull() ?: return null

    if (minutes > 59 || seconds > 59) return null

    // Avoid overflow when converting a very large typed hour value.
    if (hours > (Long.MAX_VALUE - 3599) / 3600) return null

    return hours * 3600 + minutes * 60 + seconds
}

private fun RunningStates.withCalculatedPace(secondsPerKm: Long): RunningStates =
    copy(
        pace = pace.copy(
            minute = (secondsPerKm / 60).toString(),
            second = (secondsPerKm % 60).toString().padStart(2, '0')
        )
    )

private fun RunningStates.withCalculatedTime(seconds: Long): RunningStates =
    copy(
        time = time.copy(
            hour = (seconds / 3600).toString(),
            minute = ((seconds % 3600) / 60).toString().padStart(2, '0'),
            second = (seconds % 60).toString().padStart(2, '0')
        )
    )

private fun RunningStates.paceSecondsOrNull(): Long? {
    val minutes = pace.minute.nonNegativeLongOrNull() ?: return null
    val seconds = pace.second.nonNegativeLongOrNull() ?: return null

    if (seconds > 59 || minutes > Long.MAX_VALUE / 60) return null
    return minutes * 60 + seconds
}

private fun RunningStates.clearCalculatedTimeAndSpeed(): RunningStates =
    copy(
        time = time.copy(
            hour = "",
            minute = "",
            second = ""
        ),
        speed = ""
    )

private fun RunningStates.clearCalculatedTimeAndPace(): RunningStates =
    copy(
        time = time.copy(
            hour = "",
            minute = "",
            second = ""
        ),
        pace = pace.copy(
            minute = "",
            second = ""
        )
    )