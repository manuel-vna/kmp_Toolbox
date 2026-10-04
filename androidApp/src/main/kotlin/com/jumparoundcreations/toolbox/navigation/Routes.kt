package com.jumparoundcreations.toolbox.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object Dashboard : Routes

    @Serializable
    data object PaceCalculator : Routes

    @Serializable
    data object Unknown : Routes
}
