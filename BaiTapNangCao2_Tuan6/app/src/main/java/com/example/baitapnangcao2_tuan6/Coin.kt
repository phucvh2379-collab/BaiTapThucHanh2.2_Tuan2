package com.example.baitapnangcao2_tuan6 // đổi lại theo package của em

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double,
    val price_change_percentage_24h: Double
)
