package com.shalenmathew.nexblock.crypto.domain.repository

import com.shalenmathew.nexblock.core.domain.util.NetworkError
import com.shalenmathew.nexblock.core.domain.util.Result
import com.shalenmathew.nexblock.crypto.domain.model.Coin

interface CoinRepository {

    suspend fun coinList(): Result<List<Coin>, NetworkError>

}