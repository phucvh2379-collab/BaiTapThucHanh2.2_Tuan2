package com.example.baitapthuchanh1_tuan5

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Enum định nghĩa các trạng thái xác thực
enum class AuthState {
    INITIAL,    // Trạng thái ban đầu: Chỉ hiện nút Đăng nhập
    LOADING,    // Đang chờ Firebase phản hồi
    SUCCESS,    // Thành công: Hiển thị thông tin người dùng
    ERROR       // Lỗi: Hiển thị thông báo lỗi
}

class AuthViewModel : ViewModel() {

    // Trạng thái hiện tại của UI
    var authState by mutableStateOf(AuthState.INITIAL)
        private set

    // Thông tin người dùng (mô phỏng)
    var userEmail by mutableStateOf("sample@gmail.com")
        private set

    // Hàm mô phỏng quá trình đăng nhập Google
    fun signInWithGoogle() {
        // Chuyển sang trạng thái LOADING để hiển thị quá trình xử lý (nếu cần)
        authState = AuthState.LOADING

        // Mô phỏng việc gọi API Firebase (giả định kết quả thành công sau 2 giây)
        // Trong môi trường thực tế, bạn sẽ gọi FirebaseAuth.getInstance().signIn...

        // Để demo, chúng ta sẽ chuyển sang trạng thái thành công/lỗi sau vài giây:

        // **DEMO 1: THÀNH CÔNG** (Bỏ ghi chú để thử)
        // authState = AuthState.SUCCESS

        // **DEMO 2: LỖI** (Bỏ ghi chú để thử, đây là trạng thái ban đầu để demo lỗi)
        authState = AuthState.ERROR
    }

    // Hàm reset trạng thái để quay về màn hình ban đầu
    fun resetState() {
        authState = AuthState.INITIAL
    }

    // Hàm mô phỏng lỗi (dùng cho mục đích hiển thị UI)
    fun getErrorMessage(): String {
        return "Google Sign-in Failed\nUser canceled the Google sign-in process."
    }

    // Hàm mô phỏng thành công (dùng cho mục đích hiển thị UI)
    fun getSuccessMessage(): String {
        return "Success!\nHi ${userEmail}\nWelcome to UTHSmartTasks"
    }
}