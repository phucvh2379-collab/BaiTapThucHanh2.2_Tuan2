package com.example.uthsmarttasks.Screens.ForgetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun OtpInput(
    otpLength: Int = 6,
    onFilled: (String) -> Unit
) {
    var otpValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = otpValue,
            onValueChange = {
                if (it.length <= otpLength && it.all { c -> c.isDigit() }) {
                    otpValue = it
                    if (it.length == otpLength) onFilled(it)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            decorationBox = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(otpLength) { index ->
                        val char = otpValue.getOrNull(index)?.toString() ?: ""
                        val isFocused = index == otpValue.length

                        Box(
                            modifier = Modifier
                                .size(55.dp) // Tăng kích thước ô
                                .clip(RoundedCornerShape(14.dp)) // Bo tròn mềm hơn
                                .background(
                                    if (char.isNotEmpty()) Color.White
                                    else Color(0xFFF0F3FF) // Light Blue pastel nhẹ
                                )
                                .border(
                                    width = 2.dp,
                                    color = if (isFocused) Color(0xFF0064D2) // Màu xanh UTH
                                    else Color(0xFFBFC6D4),
                                    shape = RoundedCornerShape(14.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                style = MaterialTheme.typography.headlineMedium, // Chữ to hơn
                                color = Color(0xFF002A64) // Xanh Navy chủ đạo
                            )
                        }
                    }
                }
            }
        )
    }
}
