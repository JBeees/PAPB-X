package com.example.laprak

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    // READ: Flow dari Repository yang bisa diobservasi oleh UI
    val allTasks = repository.allTasks
    // CREATE: Panggil Repository di dalam viewModelScope
    fun addNewTask(taskTitle: String) {
        val newTask = Task(title = taskTitle)
        viewModelScope.launch {
            repository.insert(newTask)
        }
    }
    // UPDATE: Panggil Repository di dalam viewModelScope
    fun updateTaskStatus(task: Task, isCompleted: Boolean) {
        val updatedTask = task.copy(isCompleted = isCompleted)
        viewModelScope.launch {
            repository.update(updatedTask)
        }
    }
    fun updateTitleTask(task: Task, title: String) {
        val updatedTitle = task.copy(title = title)
        viewModelScope.launch {
            repository.updateTitle(updatedTitle)
        }
    }

    // DELETE: Panggil Repository di dalam viewModelScope
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}
// Factory untuk membuat instance ViewModel
class TaskViewModelFactory(private val repository: TaskRepository) :
    androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}