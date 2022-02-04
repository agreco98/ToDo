package com.example.todo.todolist

import com.example.todo.data.model.ToDo

data class ToDoListState(
    val allToDo: List<ToDo> = emptyList(),
    val isFinished: Boolean = false
)
