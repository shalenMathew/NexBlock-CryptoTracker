package com.shalenmathew.nexblock.crypto.presentation.model

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.shalenmathew.nexblock.core.presentation.getDrawableIdForCoin

import com.shalenmathew.nexblock.crypto.domain.model.Coin
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayedNumbers,
    val priceUsd: DisplayedNumbers,
    val changePercent24Hr: DisplayedNumbers,
    @DrawableRes val iconRes: Int
)

data class DisplayedNumbers(
    val value: Double,
    val displayValue: String
)

fun Coin.toCoinUi(): CoinUi {

 return  CoinUi(
        id=id,
        name = name,
        rank = rank,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
     iconRes = getDrawableIdForCoin(symbol = symbol)
        )

}



fun Double.toDisplayableNumber(): DisplayedNumbers{
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayedNumbers(
        value = this,
        displayValue = formatter.format(this)
    )
}

