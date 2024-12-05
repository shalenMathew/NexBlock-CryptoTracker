package com.shalenmathew.nexblock.crypto.presentation.coin_list

import com.shalenmathew.nexblock.crypto.domain.model.Coin
import com.shalenmathew.nexblock.crypto.presentation.model.CoinUi

sealed interface CoinListActions {
    data class onClick(val coinUi: CoinUi): CoinListActions
}