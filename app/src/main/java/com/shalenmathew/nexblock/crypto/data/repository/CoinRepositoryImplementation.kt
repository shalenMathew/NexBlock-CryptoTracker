package com.shalenmathew.nexblock.crypto.data.repository

import com.shalenmathew.nexblock.core.data.networking.constructUrl
import com.shalenmathew.nexblock.core.data.networking.safeCall
import com.shalenmathew.nexblock.core.domain.util.NetworkError
import com.shalenmathew.nexblock.core.domain.util.Result
import com.shalenmathew.nexblock.core.domain.util.map
import com.shalenmathew.nexblock.crypto.data.dto.CoinHistoryDto
import com.shalenmathew.nexblock.crypto.data.dto.CoinResponseDto
import com.shalenmathew.nexblock.crypto.data.mappers.toCoin
import com.shalenmathew.nexblock.crypto.data.mappers.toCoinPrice
import com.shalenmathew.nexblock.crypto.domain.model.Coin
import com.shalenmathew.nexblock.crypto.domain.model.CoinPrice
import com.shalenmathew.nexblock.crypto.domain.repository.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class CoinRepositoryImplementation(private val httpClient: HttpClient): CoinRepository {

    override suspend fun coinList(): Result<List<Coin>, NetworkError> {
      return  safeCall<CoinResponseDto> {
          httpClient.get( //  executes GET request with the specified url
              urlString = constructUrl("/assets")
          )
      }.map { coinResponseDto->
          coinResponseDto.data.map { it.toCoin() }
      }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {

        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

      return safeCall<CoinHistoryDto> {
          httpClient.get(
              urlString = constructUrl("/assets/$coinId/history")
          ){
              parameter("interval", "h6")
              parameter("start", startMillis)
              parameter("end", endMillis)
          }
      }.map { coinHistoryDto->
          coinHistoryDto.data.map { coinPriceDto->
              coinPriceDto.toCoinPrice()
          }
      }

    }
}