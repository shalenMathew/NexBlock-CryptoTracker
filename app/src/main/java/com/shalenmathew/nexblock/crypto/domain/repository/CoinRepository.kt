package com.shalenmathew.nexblock.crypto.domain.repository

import com.shalenmathew.nexblock.core.domain.util.NetworkError
import com.shalenmathew.nexblock.core.domain.util.Result
import com.shalenmathew.nexblock.crypto.domain.model.Coin
import com.shalenmathew.nexblock.crypto.domain.model.CoinPrice
import java.time.ZonedDateTime

interface CoinRepository {

    suspend fun coinList(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(   coinId: String,
                                  start: ZonedDateTime,
                                  end: ZonedDateTime): Result<List<CoinPrice>, NetworkError>

}