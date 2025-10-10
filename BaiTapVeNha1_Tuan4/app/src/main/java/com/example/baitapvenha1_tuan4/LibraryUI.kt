package com.example.baitapvenha1_tuan4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitapvenha1_tuan4.ui.theme.BaiTapVeNha1_Tuan4Theme
import androidx.compose.ui.text.style.TextAlign

// SỬA LỖI: Thêm annotation cho các API thử nghiệm (TopAppBar, NavigationBar)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = viewModel()
) {
    var showStudentDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hệ thống Quản lý Thư viện", fontWeight = FontWeight.Bold) }
            )
        },
        bottomBar = {
            BottomNavBar()
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Lấy tên sinh viên hiện tại từ ViewModel
            StudentSection(
                studentName = viewModel.currentStudent.name,
                onChangeClick = { showStudentDialog = true }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Danh sách sách",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Lấy danh sách sách mượn từ ViewModel
            BookListSection(
                books = viewModel.borrowedBooks,
                modifier = Modifier.weight(1f)
            )

            // Nút Thêm
            Button(
                onClick = { viewModel.addBook() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text("Thêm", fontSize = 18.sp)
            }
        }
    }

    // Dialog Thay đổi Sinh viên
    if (showStudentDialog) {
        StudentSelectionDialog(
            students = viewModel.getAllStudents(),
            onStudentSelected = { newStudent ->
                viewModel.selectNewStudent(newStudent) // <--- GỌI HÀM ĐÃ ĐỔI TÊN
                showStudentDialog = false
            },
            onDismiss = { showStudentDialog = false }
        )
    }
}

// --- CÁC HÀM COMPOSABLE PHỤ KHÁC ---

@Composable
fun StudentSelectionDialog(
    students: List<Student>,
    onStudentSelected: (Student) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Chọn Sinh viên") },
        text = {
            LazyColumn {
                items(students, key = { it.id }) { student ->
                    Text(
                        text = student.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onStudentSelected(student) }
                            .padding(vertical = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Hủy") }
        }
    )
}

@Composable
fun StudentSection(studentName: String, onChangeClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Sinh viên", fontSize = 16.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = studentName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(end = 8.dp)
            )
            Button(onClick = onChangeClick, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))) {
                Text("Thay đổi")
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = { /* Logic trả sách */ },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF007AFF))
            )
            Text(
                text = book.name,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BookListSection(books: List<Book>, modifier: Modifier = Modifier) {
    if (books.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE0E0E0))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                color = Color.Gray,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(books, key = { it.id }) { book ->
                BookItem(book = book)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // SỬA LỖI
@Composable
fun BottomNavBar() {
    NavigationBar(containerColor = Color.White, tonalElevation = 5.dp) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Quản lý") },
            label = { Text("Quản lý") },
            selected = true, onClick = { /* ... */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.MenuBook, contentDescription = "DS Sách") },
            label = { Text("DS Sách") },
            selected = false, onClick = { /* ... */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Sinh viên") },
            label = { Text("Sinh viên") },
            selected = false, onClick = { /* ... */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    BaiTapVeNha1_Tuan4Theme { LibraryScreen() }
}