package com.example.baitapthuchanh1_tuan5

import androidx.annotation.DrawableRes

// Data class mô hình hóa nội dung của một màn hình giới thiệu (OOP)
data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val isFinal: Boolean = false // Xác định có phải màn hình cuối cùng (Get Started)
)

// Dữ liệu mẫu cho 4 trang
object OnboardingData {
    val pages = listOf(
        // Màn hình 1: Splash Screen
        OnboardingPage(
            title = "OnBoard Flow - P01", // Tiêu đề từ hình ảnh (có thể thay đổi)
            description = "UTH SmartTasks", // Mô tả từ hình ảnh
            imageResId = R.drawable.ic_splash_screen, // LƯU Ý: Cần tạo file này
            isFinal = false
        ),
        // Màn hình 2: Easy Time Management
        OnboardingPage(
            title = "Get started - Task Mangement",
            description = "With management based on priority and daily tasks, it will give you convenience in managing all those pending tasks that must be done in life.",
            imageResId = R.drawable.ic_time_management, // LƯU Ý: Cần tạo file này
            isFinal = false
        ),
        // Màn hình 3: Increase Work Effectiveness
        OnboardingPage(
            title = "Get started second - Task Mangement",
            description = "Time management and the determination of more important tasks will give your job statistics better and always improve.",
            imageResId = R.drawable.ic_work_effectiveness, // LƯU Ý: Cần tạo file này
            isFinal = false
        ),
        // Màn hình 4: Reminder Notification (Màn hình cuối)
        OnboardingPage(
            title = "Get started third - Task Mangement",
            description = "The advantage of this application is that it also provides reminders for you so your daily life doesn't keep you forgetting important work and save more time you have set.",
            imageResId = R.drawable.ic_reminder, // LƯU Ý: Cần tạo file này
            isFinal = true
        )
    )
}