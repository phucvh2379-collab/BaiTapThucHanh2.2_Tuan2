package com.example.baitapvenha1_tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.baitapvenha1_tuan4.ui.theme.BaiTapVeNha1_Tuan4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapVeNha1_Tuan4Theme {
                // Gọi màn hình chính
                LibraryScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}