package com.example.baitapnangcao2_tuan6

import retrofit2.http.GET

interface BinanceApi {
    @GET("api/v3/ticker/24hr?symbol=BTCUSDT")
    suspend fun getTicker(): BinanceTicker
}

data class BinanceTicker(
    val lastPrice: String
)
