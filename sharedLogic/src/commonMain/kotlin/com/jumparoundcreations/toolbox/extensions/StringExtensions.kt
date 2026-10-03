package com.jumparoundcreations.toolbox.extensions

fun String.positiveDoubleOrNull(): Double? = trim().toDoubleOrNull()?.takeIf { it.isFinite() && it > 0.0 }

fun String.nonNegativeLongOrNull(): Long? = if (isBlank()) 0L else toLongOrNull()?.takeIf { it >= 0 }