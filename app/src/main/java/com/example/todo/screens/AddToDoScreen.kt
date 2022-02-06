package com.example.todo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.todo.addtodo.AddToDoComponent

@Composable
fun AddToDoScreen(
    selectedId: Long,
    onNavigation: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
                navigationIcon = {
                   Icon(
                       imageVector = Icons.Default.ArrowBack,
                       contentDescription = "Back",
                       tint = MaterialTheme.colors.onBackground,
                       modifier = Modifier
                           .clip(CircleShape)
                           .clickable {
                               onNavigation()
                           }
                           .padding(top = 16.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
                           .size(24.dp))
                }
            )
        }
    ) {
        AddToDoComponent(
            onNavigation = {  onNavigation()  }
        )
    }
}

