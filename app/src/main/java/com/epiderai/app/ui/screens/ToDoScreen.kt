package com.epiderai.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Task(val name: String, var isCompleted: Boolean = false)

@Composable
fun ToDoScreen() {
    val tasks = remember {
        mutableStateListOf(
            Task("Morning Cleanser"),
            Task("Apply Sunscreen"),
            Task("Drink 2L Water"),
            Task("Night Cream Application")
        )
    }
    val completedTasks = tasks.count { it.isCompleted }
    val progress = if (tasks.isNotEmpty()) completedTasks.toFloat() / tasks.size.toFloat() else 0f

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Daily Routine", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Overall Progress
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = progress,
                    modifier = Modifier.size(120.dp),
                    strokeWidth = 8.dp,
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))


        Text("Progress: ${completedTasks}/${tasks.size}", style = MaterialTheme.typography.bodyMedium)
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks) { task ->
                TaskItem(task = task, onTaskCheckedChanged = { isChecked ->
                    val index = tasks.indexOf(task)
                    if (index != -1) {
                        tasks[index] = task.copy(isCompleted = isChecked)
                    }
                })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskCheckedChanged: (Boolean) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(task.name, modifier = Modifier.weight(1f))
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onTaskCheckedChanged,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF4CAF50), // Green
                    uncheckedColor = Color(0xFFF44336) // Red
                )
            )
        }
    }
}
