package com.example.baitapnangcao2_tuan6

import retrofit2.http.GET

interface KrakenApi {
    @GET("0/public/Ticker?pair=XBTUSD")
    suspend fun getTicker(): KrakenResponse
}

data class KrakenResponse(
    val result: Map<String, KrakenTicker>
)

data class KrakenTicker(
    val c: List<String>
)
