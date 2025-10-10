package com.example.baitapnangcao2_tuan6

import retrofit2.http.GET

interface CoinGeckoApi {
    @GET("api/v3/coins/bitcoin")
    suspend fun getBitcoin(): CoinGeckoResponse
}

data class CoinGeckoResponse(
    val id: String,
    val symbol: String,
    val name: String,
    val image: CoinImage,
    val market_data: MarketData
)

data class CoinImage(val large: String)
data class MarketData(val current_price: Map<String, Double>)
