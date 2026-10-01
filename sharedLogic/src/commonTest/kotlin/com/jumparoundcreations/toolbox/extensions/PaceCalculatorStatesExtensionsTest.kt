package com.jumparoundcreations.toolbox.extensions

import com.jumparoundcreations.toolbox.paceCalculator.PaceCalculatorStates
import kotlin.test.Test
import kotlin.test.assertEquals

class PaceCalculatorStatesExtensionsTest {

    @Test
    fun testFormatToTimeString_nullAndNegative() {
        assertEquals("", formatToTimeString(null))
        assertEquals("", formatToTimeString(0L))
        assertEquals("", formatToTimeString(-10L))
    }

    @Test
    fun testFormatToTimeString_underOneHour() {
        assertEquals("0:50", formatToTimeString(50L))
        assertEquals("5:00", formatToTimeString(300L))
        assertEquals("25:00", formatToTimeString(1500L))
        assertEquals("50:00", formatToTimeString(3000L))
    }

    @Test
    fun testFormatToTimeString_oneHourAndAbove() {
        assertEquals("1:00:00", formatToTimeString(3600L))
        assertEquals("1:01:05", formatToTimeString(3665L))
        assertEquals("1:45:29", formatToTimeString(6329L))
    }

    @Test
    fun testWithPopularDistances_validPace() {
        val state = PaceCalculatorStates()
        val result = state.withPopularDistances(300L) // 5:00 min/km pace

        assertEquals("5:00", result.popularOneKm)
        assertEquals("25:00", result.popularFiveKm)
        assertEquals("50:00", result.popularTenKm)
        assertEquals("1:45:29", result.popularTwentyOneKm)
    }

    @Test
    fun testWithPopularDistances_nullPace() {
        val state = PaceCalculatorStates()
        val result = state.withPopularDistances(null)

        assertEquals("", result.popularOneKm)
        assertEquals("", result.popularFiveKm)
        assertEquals("", result.popularTenKm)
        assertEquals("", result.popularTwentyOneKm)
    }
}
