package com.example.todo.addtodo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todo.data.model.ToDo

@Composable
fun AddToDoComponent(
    onNavigation: () -> Unit,
    viewModel: AddToDoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    AddToDoSection(
        title = state.title,
        onTitleChange = {  viewModel.onTitleChange(it)  },
        description = state.description,
        onDescriptionChange = {  viewModel.onDescriptionChange(it)  },
        onInsertToDo = {  viewModel.insertToDo(it)  },
        onNavigation = {  onNavigation()  }
    )
}