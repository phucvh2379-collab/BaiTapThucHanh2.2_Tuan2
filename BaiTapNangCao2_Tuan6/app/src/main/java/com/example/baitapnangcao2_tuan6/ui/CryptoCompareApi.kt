package com.example.baitapnangcao2_tuan6

import retrofit2.http.GET

interface CryptoCompareApi {
    @GET("data/pricemultifull?fsyms=BTC&tsyms=USD")
    suspend fun getPrice(): CryptoCompareResponse
}

data class CryptoCompareResponse(
    val RAW: Map<String, Map<String, CryptoCompareUSD>>
)

data class CryptoCompareUSD(
    val PRICE: Double
)
