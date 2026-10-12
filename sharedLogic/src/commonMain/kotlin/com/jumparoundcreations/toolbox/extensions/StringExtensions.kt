package com.jumparoundcreations.toolbox.extensions

fun String.positiveDoubleOrNull(): Double? = trim().toDoubleOrNull()?.takeIf { it.isFinite() && it > 0.0 }

fun String.nonNegativeLongOrNull(): Long? = if (isBlank()) 0L else toLongOrNull()?.takeIf { it >= 0 }

/*
 * Validates whether the string is a valid intermediate or complete numeric input for fields
 * such as distance, pace, or speed.
 *
 * Supports:
 * - Empty string "" (field deletion)
 * - Whole numbers up to [maxIntegerDigits] (default 3, e.g. 0 to 999)
 * - Trailing decimal separator '.' or ',' while actively typing
 * - Up to [maxDecimalDigits] decimal places (default 2, e.g. xx.xx or xx,xx)
 */
fun String.distanceAndSpeedFilter(): Boolean {
    if (isEmpty()) return true
    val pattern = "^\\d{1,3}([.,]\\d{0,2})?$"
    return this.matches(Regex(pattern))
}

/*
 * Validates whether the string is a valid integer hour time field input .
 */
fun String.hourInputFilter(): Boolean {
    if (isEmpty()) return true
    val pattern = "^\\d{1,3}$"
    return matches(Regex(pattern))
}

/*
 * Validates whether the string is a number between 1 and 59
 * ^ and $ ensure the whole input is only that number
 * * 1-9 OR 10-59
 */
fun String.minuteSecondInputFilter(): Boolean {
    if (isEmpty()) return true
    val pattern = "^([0-9]|[0-5][0-9])\$"
    return matches(Regex(pattern))
}
