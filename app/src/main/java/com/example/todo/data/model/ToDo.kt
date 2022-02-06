package com.example.todo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    val title: String,
    val description: String,
    val isFinished: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
