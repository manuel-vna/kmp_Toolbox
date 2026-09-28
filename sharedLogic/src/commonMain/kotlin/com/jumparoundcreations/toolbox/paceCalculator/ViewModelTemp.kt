package com.jumparoundcreations.toolbox.paceCalculator

/*
data class CompetitionUiState(
    val timeHour: String = "",
    val timeMinute: String = "",
    val timeSecond: String = "",
    val distanceKm: String = "",
    val paceMinute: String = "",
    val paceSecond: String = "",
    val speedKmPerHour: String = "",
)

class CompetitionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CompetitionUiState())
    val uiState = _uiState.asStateFlow()

    fun onDistanceChanged(value: String) {
        _uiState.update { state ->
            state.copy(distanceKm = value).calculateFromDistanceAndTime()
        }
    }

    fun onHourChanged(value: String) =
        updateTime { it.copy(timeHour = value) }

    fun onMinuteChanged(value: String) =
        updateTime { it.copy(timeMinute = value) }

    fun onSecondChanged(value: String) =
        updateTime { it.copy(timeSecond = value) }

    private fun updateTime(
        change: (CompetitionUiState) -> CompetitionUiState
    ) {
        _uiState.update { state ->
            change(state).calculateFromDistanceAndTime()
        }
    }

    fun onPaceMinuteChanged(value: String) =
        updatePace { it.copy(paceMinute = value) }

    fun onPaceSecondChanged(value: String) =
        updatePace { it.copy(paceSecond = value) }

    private fun updatePace(
        change: (CompetitionUiState) -> CompetitionUiState
    ) {
        _uiState.update { state ->
            change(state).calculateFromPace()
        }
    }

    fun onSpeedChanged(value: String) {
        _uiState.update { state ->
            state.copy(speedKmPerHour = value).calculateFromSpeed()
        }
    }
}

private fun CompetitionUiState.calculateFromPace(): CompetitionUiState {
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

    return withCalculatedTime(calculatedTime.roundToLong()).copy(
        speedKmPerHour = (3600.0 / paceSeconds).displayDecimal()
    )
}

private fun CompetitionUiState.calculateFromSpeed(): CompetitionUiState {
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


private fun CompetitionUiState.paceSecondsOrNull(): Long? {
    val minutes = paceMinute.nonNegativeLongOrNull() ?: return null
    val seconds = paceSecond.nonNegativeLongOrNull() ?: return null

    if (seconds > 59 || minutes > Long.MAX_VALUE / 60) return null
    return minutes * 60 + seconds
}


private fun CompetitionUiState.clearCalculatedTimeAndSpeed(): CompetitionUiState =
    copy(
        timeHour = "",
        timeMinute = "",
        timeSecond = "",
        speedKmPerHour = ""
    )

private fun CompetitionUiState.clearCalculatedTimeAndPace(): CompetitionUiState =
    copy(
        timeHour = "",
        timeMinute = "",
        timeSecond = "",
        paceMinute = "",
        paceSecond = ""
    )

*/
