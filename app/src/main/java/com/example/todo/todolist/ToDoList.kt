package com.example.todo.todolist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todo.data.model.ToDo
import com.example.todo.util.Screen

@Composable
fun ToDoList(
    viewModel: ToDoListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

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