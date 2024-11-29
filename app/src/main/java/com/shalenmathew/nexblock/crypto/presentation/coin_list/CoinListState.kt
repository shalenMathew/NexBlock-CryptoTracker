package com.shalenmathew.nexblock.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.shalenmathew.nexblock.crypto.presentation.model.CoinUi


data class CoinListState(
var isLoading: Boolean = false,
    var listOfCoins: List<CoinUi> = emptyList(),
    var selectedCoin: CoinUi? = null
)