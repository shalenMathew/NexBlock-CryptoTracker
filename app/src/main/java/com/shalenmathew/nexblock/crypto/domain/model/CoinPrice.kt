package com.shalenmathew.nexblock.crypto.domain.model

import java.time.ZonedDateTime

data class CoinPrice (
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)