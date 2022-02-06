package com.example.todo.util

sealed class Screen(val route: String) {
    object ToDoListScreen: Screen("toDo_list_route")
    object AddToDoScreen: Screen("add_ToDo_route")
}
