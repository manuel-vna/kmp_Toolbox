package com.jumparoundcreations.toolbox.running

sealed class RunningIntents {

    data class ChangeDistance(
        val newDistance: String
    ) : RunningIntents()

    data class ChangeTimeHour(
        val newTimeHour: String
    ) : RunningIntents()

    data class ChangeTimeMinute(
        val newTimeMinute: String
    ) : RunningIntents()

    data class ChangeTimeSecond(
        val newTimeSecond: String
    ) : RunningIntents()

    data class ChangePaceMinute(
        val newPaceMinute: String
    ) : RunningIntents()

    data class ChangePaceSecond(
        val newPaceSecond: String
    ) : RunningIntents()

    data class ChangeSpeed(
        val newSpeed: String
    ) : RunningIntents()
}
