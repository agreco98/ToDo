package com.example.todo.addtodo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.data.model.ToDo

@Composable
fun AddToDoSection(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onInsertToDo: (ToDo) -> Unit,
    onNavigation: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = title,
            onValueChange ={  onTitleChange(it)  },
            label = { Text(text = "Enter title") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange ={  onDescriptionChange(it)  },
            label = { Text(text = "Enter description") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = {
                    onNavigation()
                }
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.button
                )
            }
            Spacer(Modifier.width(16.dp))
            Button(
                onClick = {
                    val toDo = ToDo(title, description)
                    onInsertToDo(toDo)
                    onNavigation()
                },
                modifier = Modifier
            ) {
                Text(
                    text = "insert",
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}