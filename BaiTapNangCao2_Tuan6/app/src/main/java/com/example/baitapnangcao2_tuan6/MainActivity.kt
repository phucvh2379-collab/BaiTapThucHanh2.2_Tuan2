package com.example.baitapnangcao2_tuan6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = CryptoViewModel()
        vm.loadCoins() // <<< bắt buộc

        setContent {
            val coins by vm.coins.collectAsState()
            com.example.baitapnangcao2_tuan6.ui.CryptoScreen(coins)
        }
    }
}
