package com.example.todo.addtodo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Add task",
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(20.dp))
            TextField(
                value = title,
                onValueChange ={  onTitleChange(it)  },
                label = { Text(text = "Enter task") },
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background.copy(ContentAlpha.medium),
                    cursorColor = MaterialTheme.colors.onBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                    ),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            TextField(
                value = description,
                onValueChange ={  onDescriptionChange(it)  },
                label = { Text(text = "Enter description") },
                singleLine = false,
                maxLines = 3,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background.copy(ContentAlpha.medium),
                    cursorColor = MaterialTheme.colors.onBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    onClick = {
                        onNavigation()
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground.copy(ContentAlpha.medium),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        val toDo = ToDo(title, description)
                        onInsertToDo(toDo)
                        onNavigation()
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Text(
                        text = "Add task",
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}