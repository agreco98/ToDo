package com.example.todo.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                title = {
                    Text(
                        text = "To Do",
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .padding(top = 16.dp)
                ) },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small,
                onClick = {  navController.navigate(Screen.AddToDoScreen.route) }
            ) {
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
