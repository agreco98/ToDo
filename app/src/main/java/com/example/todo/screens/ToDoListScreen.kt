package com.example.todo.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.todo.data.model.ToDo
import com.example.todo.todolist.ToDoList
import com.example.todo.util.Screen

@Composable
fun ToDoListScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("To Do") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  navController.navigate(Screen.AddToDoScreen.route)  }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task"
                )
            }
        }
    ) {
        ToDoList()
    }
}
