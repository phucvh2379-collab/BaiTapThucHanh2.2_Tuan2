package com.example.baitapthuchanh1_tuan5

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitapthuchanh1_tuan5.ui.theme.BaiTapThucHanh1_Tuan5Theme

// Màn hình chính
@Composable
fun OnboardingScreen(
    page: OnboardingPage,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onSkipClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            OnboardingTopBar(
                title = page.title,
                onSkipClicked = onSkipClicked
            )
        },
        bottomBar = {
            OnboardingBottomBar(
                isFinal = page.isFinal,
                // Ẩn nút back ở màn hình đầu tiên
                showBack = page.title != OnboardingData.pages.first().title,
                onNextClicked = onNextClicked,
                onBackClicked = onBackClicked
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hình ảnh minh họa (Bạn cần tạo các file drawable tương ứng)
            // Tạm thời dùng một placeholder nếu bạn chưa tạo file:
            // Image(painter = painterResource(id = android.R.drawable.ic_menu_help), ...)

            // Dùng ID của Page (Giả định bạn đã tạo file drawable)
            Image(
                painter = painterResource(id = page.imageResId),
                contentDescription = page.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(vertical = 32.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Tiêu đề
            Text(
                text = page.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Mô tả
            Text(
                text = page.description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// Thanh Top Bar (Màu đen)
@Composable
fun OnboardingTopBar(title: String, onSkipClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nút Skip
        Text(
            text = "Skip",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onSkipClicked() }
        )
    }
}

// Thanh Bottom Bar (Nút Back và Next/Get Started)
@Composable
fun OnboardingBottomBar(
    isFinal: Boolean,
    showBack: Boolean,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nút Back
        if (showBack) {
            Button(
                onClick = onBackClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(0.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        } else {
            Spacer(modifier = Modifier.width(48.dp)) // Đảm bảo vị trí nút Next cố định
        }

        // Nút Next / Get Started
        Button(
            onClick = onNextClicked,
            modifier = Modifier
                .width(150.dp)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = if (isFinal) "Get Started" else "Next",
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    BaiTapThucHanh1_Tuan5Theme {
        OnboardingScreen(
            page = OnboardingData.pages[1],
            onNextClicked = {},
            onBackClicked = {},
            onSkipClicked = {}
        )
    }
}