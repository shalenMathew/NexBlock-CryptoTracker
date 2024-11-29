package com.shalenmathew.nexblock.crypto.data.repository

import com.shalenmathew.nexblock.core.data.networking.constructUrl
import com.shalenmathew.nexblock.core.data.networking.safeCall
import com.shalenmathew.nexblock.core.domain.util.NetworkError
import com.shalenmathew.nexblock.core.domain.util.Result
import com.shalenmathew.nexblock.core.domain.util.map
import com.shalenmathew.nexblock.crypto.data.dto.CoinResponseDto
import com.shalenmathew.nexblock.crypto.data.mappers.toCoin
import com.shalenmathew.nexblock.crypto.domain.model.Coin
import com.shalenmathew.nexblock.crypto.domain.repository.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoinRepositoryImplementation(private val httpClient: HttpClient): CoinRepository {

    override suspend fun coinList(): Result<List<Coin>, NetworkError> {
      return  safeCall<CoinResponseDto> {
          httpClient.get(
              urlString = constructUrl("/assets")
          )
      }.map { coinResponseDto->
          coinResponseDto.data.map { it.toCoin() }
      }
    }
}