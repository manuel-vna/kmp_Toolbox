package com.jumparoundcreations.toolbox.extensions

import kotlin.math.roundToInt

fun Double.displayDecimal(): String = ((this * 10).roundToInt() / 10.0).toString()
