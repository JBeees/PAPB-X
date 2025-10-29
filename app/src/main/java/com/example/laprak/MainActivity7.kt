package com.example.laprak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity7 : ComponentActivity() {
    // Inisialisasi Database dan ViewModel
    private val database by lazy { TaskDatabase.getDatabase(application) }
    private val repository by lazy { TaskRepository(database.taskDao()) }
    private val viewModelFactory by lazy { TaskViewModelFactory(repository) }
    private val viewModel: TaskViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
// Ambil data Flow dan konversi menjadi Compose State
                val tasks = viewModel.allTasks.collectAsState(
                    initial = emptyList()
                ).value
                TaskScreen(
                    tasks = tasks,
                    onAddTask = { title -> viewModel.addNewTask(title) },
                    onUpdateTask = { task, completed ->
                        viewModel.updateTaskStatus(task, completed)
                    },
                    onDeleteTask = { task -> viewModel.deleteTask(task) },
                    onUpdateTitleTask = { task, title ->
                        viewModel.updateTitleTask(task, title)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    tasks: List<Task>,
    onAddTask: (String) -> Unit,
    onUpdateTask: (Task, Boolean) -> Unit,
    onDeleteTask: (Task) -> Unit,
    onUpdateTitleTask: (Task, String) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Daftar Tugas (Room Compose)") }) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            TaskInput(onAddTask)
            Spacer(modifier = Modifier.height(8.dp))
            TaskList(tasks, onUpdateTask, onDeleteTask, onUpdateTitleTask)
        }
    }
}

@Composable
fun TaskInput(onAddTask: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Tugas Baru") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                if (text.isNotBlank()) {
                    onAddTask(text)
                    text = ""
                }
            },
            enabled = text.isNotBlank()
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Tambah Tugas")
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onUpdateTask: (Task, Boolean) -> Unit,
    onDeleteTask: (Task) -> Unit,
    onUpdateTitleTask: (Task, String) -> Unit
) {
// LazyColumn menggantikan RecyclerView
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(tasks, key = { it.id }) { task ->
            TaskItem(
                task = task,
                onCheckedChange = { isChecked -> onUpdateTask(task, isChecked) },
                onDelete = { onDeleteTask(task) },
                onUpdateTitle = {
                    onUpdateTitleTask(task, it)
                }
            )
            Divider()
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit,
    onUpdateTitle: (String) -> Unit
) {
    var showScreen by remember { mutableStateOf(false) }
    var updatedTitle by remember { mutableStateOf(task.title) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!task.isCompleted) }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = task.title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (task.isCompleted) MaterialTheme.colorScheme.onSurfaceVariant
            else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = onDelete) {
            Icon(Icons.Filled.Delete, contentDescription = "Hapus Tugas")
        }
        IconButton(onClick = {
            updatedTitle = task.title
            showScreen = true
        }){
            Icon(Icons.Filled.Edit, contentDescription = "Update Title Tugas")
        }
    }
    if (showScreen) {
        AlertDialog(
            onDismissRequest = { showScreen = false },
            title = { Text("Edit Tugas") },
            text = {
                OutlinedTextField(
                    value = updatedTitle,
                    onValueChange = { updatedTitle = it },
                    label = { Text("Judul Tugas") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (updatedTitle.isNotBlank()) {
                            onUpdateTitle(updatedTitle)
                            showScreen = false
                        }
                    },
                    enabled = updatedTitle.isNotBlank()
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                TextButton(onClick = { showScreen = false }) {
                    Text("Batal")
                }
            }
        )
    }
}
