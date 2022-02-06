package com.example.todo.todolist

import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ToDoList(
    viewModel: ToDoListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if(state.allToDo.isEmpty()) {
        ToDoEmptyList()
    } else {
        LazyColumn() {
            items(state.allToDo) { toDo ->
                ToDoItem(
                    toDo = toDo,
                    onDelete = {  viewModel.deleteToDo(it) },
                    onChecked = { viewModel.updateIsFinished(it, toDo.id)}
                )
            }
        }
    }
}