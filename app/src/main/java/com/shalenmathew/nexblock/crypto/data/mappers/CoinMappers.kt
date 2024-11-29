package com.shalenmathew.nexblock.crypto.data.mappers

import com.shalenmathew.nexblock.crypto.data.dto.CoinDto
import com.shalenmathew.nexblock.crypto.domain.model.Coin

fun CoinDto.toCoin(): Coin{
    return Coin( id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr)
}