package com.example.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.screens.AddToDoScreen
import com.example.todo.screens.ToDoListScreen
import com.example.todo.util.Screen

@Composable
fun ToDoNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ToDoListScreen.route
    ) {
        composable(Screen.ToDoListScreen.route) {
            ToDoListScreen(navController = navController)
        }
        composable(
            Screen.AddToDoScreen.route + "?id={  id  }",
            arguments=listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )) {
            val id = it.arguments?.getLong("id")
            AddToDoScreen(
                selectedId = id ?: -1L
            ) {
                navController.navigateUp()
            }
        }
    }
}