package com.shalenmathew.nexblock.crypto.presentation.coin_detail

import android.icu.text.NumberFormat
import java.util.Locale


data class ValueLabel(
    val value: Float,
    val unit: String
) {
    fun formatted(): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            val fractionDigits = when {
                value > 1000 -> 0 // if a coin price is more than a 1000 then we dont need to show the fraction
                value in 2f..999f -> 2 // if a coin price is between 2 dollars to 999 dollars then max value after point should be 2
                else -> 3 // if the price is really small, e.g  less than 2 dollars then the max number we need after point is 3. E.g = $1.234
            }
            maximumFractionDigits = fractionDigits
            minimumFractionDigits = 0
        }
        return "${formatter.format(value)}$unit"
    }
}