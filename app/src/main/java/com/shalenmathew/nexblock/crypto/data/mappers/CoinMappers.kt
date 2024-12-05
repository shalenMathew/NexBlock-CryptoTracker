package com.shalenmathew.nexblock.crypto.data.mappers

import com.shalenmathew.nexblock.crypto.data.dto.CoinDto
import com.shalenmathew.nexblock.crypto.data.dto.CoinPriceDto
import com.shalenmathew.nexblock.crypto.domain.model.Coin
import com.shalenmathew.nexblock.crypto.domain.model.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin{
    return Coin( id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr)
}

fun CoinPriceDto.toCoinPrice(): CoinPrice{
    return CoinPrice(priceUsd =priceUsd ,dateTime= Instant
        .ofEpochMilli(time)
        .atZone(ZoneId.systemDefault()))

}