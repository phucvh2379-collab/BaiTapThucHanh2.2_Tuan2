package com.example.baitapthuchanh1_tuan5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitapthuchanh1_tuan5.ui.theme.BaiTapThucHanh1_Tuan5Theme

@Composable
fun AuthScreen(viewModel: AuthViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hệ thống Quản lý Thư viện", fontWeight = FontWeight.Bold) }
            )
        },
        containerColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 1. Nút Đăng nhập (Luôn hiển thị)
            LoginButton(
                onClick = { viewModel.signInWithGoogle() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 2. Hiển thị thông báo trạng thái dựa trên ViewModel
            when (viewModel.authState) {
                AuthState.SUCCESS -> {
                    StatusMessageCard(
                        message = viewModel.getSuccessMessage(),
                        backgroundColor = Color(0xFFADD8E6), // Màu xanh dương nhạt (giống hình)
                        onDismiss = { viewModel.resetState() } // Reset sau khi xem thành công
                    )
                }
                AuthState.ERROR -> {
                    StatusMessageCard(
                        message = viewModel.getErrorMessage(),
                        backgroundColor = Color(0xFFF08080), // Màu hồng nhạt (giống hình)
                        onDismiss = { viewModel.resetState() } // Reset sau khi xem lỗi
                    )
                }
                // Các trạng thái khác (INITIAL, LOADING) sẽ không hiển thị Card
                else -> {
                    // Có thể thêm indicator cho trạng thái LOADING nếu cần
                    if (viewModel.authState == AuthState.LOADING) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                    }
                }
            }
        }
    }
}

// Composable cho Nút "Login by Gmail"
@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF)), // Màu xanh dương đậm
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Login by Gmail", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
    }
}

// Composable cho Card Thông báo Trạng thái (Success/Error)
@Composable
fun StatusMessageCard(message: String, backgroundColor: Color, onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Nút Hủy/Đóng (Mô phỏng)
        Text(
            text = "OK / Dismiss",
            color = Color.DarkGray,
            modifier = Modifier
                .clickable(onClick = onDismiss)
                .padding(top = 4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    BaiTapThucHanh1_Tuan5Theme {
        // Có thể preview với các trạng thái khác nhau
        val viewModel = AuthViewModel()
        viewModel.authState = AuthState.ERROR // Đổi thành SUCCESS hoặc INITIAL để xem các trạng thái
        AuthScreen(viewModel)
    }
}