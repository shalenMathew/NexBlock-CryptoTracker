package com.shalenmathew.nexblock.crypto.presentation.coin_list

import com.shalenmathew.nexblock.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}