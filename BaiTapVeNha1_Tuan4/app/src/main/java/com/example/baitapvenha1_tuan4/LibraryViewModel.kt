package com.example.baitapvenha1_tuan4

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Data Models
data class Book(val id: Int, val name: String)
data class Student(val id: Int, val name: String, val borrowedBookIds: List<Int>)

class LibraryViewModel : ViewModel() {

    // --- DỮ LIỆU MẪU ---
    private val allStudents = listOf(
        Student(1, "Nguyen Van A", listOf(1, 2)),
        Student(2, "Nguyen Thi B", listOf(1)),
        Student(3, "Nguyen Van C", emptyList())
    )
    private val availableBooks = listOf(
        Book(1, "Sách 01"),
        Book(2, "Sách 02"),
        Book(3, "Sách 03"),
        Book(4, "Sách 04")
    )

    // --- TRẠNG THÁI HIỆN TẠI (Observable) ---
    var currentStudent by mutableStateOf(allStudents.first())
        private set

    var borrowedBooks = mutableStateListOf<Book>()
        private set

    init {
        updateBorrowedBooks(allStudents.first())
    }

    // --- CÁC HÀM XỬ LÝ LOGIC ---

    // 1. Chức năng Thay đổi Sinh viên (ĐÃ SỬA LỖI XUNG ĐỘT TÊN)
    fun selectNewStudent(student: Student) { // <--- Đổi tên để tránh xung đột
        currentStudent = student
        updateBorrowedBooks(student)
    }

    // 2. Chức năng Thêm Sách (Demo)
    fun addBook() {
        val book3 = availableBooks.find { it.id == 3 }
        if (book3 != null && borrowedBooks.none { it.id == book3.id }) {
            borrowedBooks.add(book3)
        }
    }

    private fun updateBorrowedBooks(student: Student) {
        borrowedBooks.clear()
        val booksForCurrentStudent = availableBooks
            .filter { it.id in student.borrowedBookIds }
        borrowedBooks.addAll(booksForCurrentStudent)
    }

    fun getAllStudents(): List<Student> = allStudents
}