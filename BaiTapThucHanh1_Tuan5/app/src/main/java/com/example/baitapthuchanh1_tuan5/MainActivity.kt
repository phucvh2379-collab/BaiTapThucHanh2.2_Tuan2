package com.example.baitapthuchanh1_tuan5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.baitapthuchanh1_tuan5.ui.theme.BaiTapThucHanh1_Tuan5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Đảm bảo bạn có thư mục theme và file Theme.kt
            BaiTapThucHanh1_Tuan5Theme {
                // Gọi màn hình Xác thực (AuthScreen)
                AuthScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
// Các hàm Greeting và GreetingPreview cũ không còn cần thiết cho bài tập này.