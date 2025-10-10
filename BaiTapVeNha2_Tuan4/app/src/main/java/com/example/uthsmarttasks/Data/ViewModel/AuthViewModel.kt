package com.example.uthsmarttasks.Data.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    // Trạng thái người dùng hiện tại (null nếu chưa đăng nhập)
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    // Trạng thái UI (Đang tải, Thành công, Lỗi)
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState

    sealed class AuthState {
        data object Initial : AuthState()
        data object Loading : AuthState()
        data class Success(val user: FirebaseUser?) : AuthState()
        data class Error(val message: String) : AuthState()
    }

    fun setAuthState(state: AuthState) {
        _authState.value = state
    }

    fun setCurrentUser(user: FirebaseUser?) {
        _currentUser.value = user
    }
}
