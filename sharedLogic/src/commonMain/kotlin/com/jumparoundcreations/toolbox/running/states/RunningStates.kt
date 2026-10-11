package com.jumparoundcreations.toolbox.running.states

data class RunningStates(
    val distance: String = "10",
    val time: Time = Time(),
    val pace: Pace = Pace(),
    val speed: String = "",
    val popularDistances: PopularDistances = PopularDistances()
)