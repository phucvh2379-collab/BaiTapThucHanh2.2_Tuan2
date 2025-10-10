package com.example.baitapnangcao2_tuan6.network

import com.example.baitapnangcao2_tuan6.Coin
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    // 🔹 Lấy danh sách coin từ CoinGecko API
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<Coin>
}
