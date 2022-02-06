package com.example.todo.todolist

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ToDoEmptyList() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AddTask,
            contentDescription = "Add Task",
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier
                .size(64.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "New task",
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Create a new task to continue your routine",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
            textAlign = TextAlign.Center
        )
    }
}