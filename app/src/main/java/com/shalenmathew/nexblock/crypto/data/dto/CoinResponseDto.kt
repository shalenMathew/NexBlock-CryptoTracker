package com.shalenmathew.nexblock.crypto.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class CoinResponseDto(
    val data: List<CoinDto>
//    val coinList: List<CoinDto>  // caution this fieldName should be same as the name in the the json data by the api or
    // it will throw error as it cannot deserialize it anymore
)