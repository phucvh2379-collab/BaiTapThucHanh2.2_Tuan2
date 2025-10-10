package com.example.baitapnangcao2_tuan6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baitapnangcao2_tuan6.network.CryptoApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel : ViewModel() {

    // 🔹 Retrofit setup
    private val api: CryptoApi = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoApi::class.java)

    // 🔹 StateFlow chứa danh sách coin
    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins: StateFlow<List<Coin>> = _coins

    // 🔹 Hàm tải dữ liệu
    fun loadCoins() {
        viewModelScope.launch {
            while (true) {
                try {
                    val data = api.getCoins()
                    _coins.value = data
                    println("✅ Dữ liệu cập nhật: ${data.size} coins")
                } catch (e: Exception) {
                    println("❌ Lỗi tải dữ liệu: ${e.message}")
                }

                // ⏱️ Cập nhật lại mỗi 10 giây
                delay(10000)
            }
        }
    }
}
