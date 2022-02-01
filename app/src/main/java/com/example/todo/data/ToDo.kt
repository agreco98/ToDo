package com.example.todo.data

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    val title: String,
    val description: String,
    val priority: String,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) {
    companion object{
        var prioritiesColors = listOf(Color.Green, Color.Yellow, Color.Red, Color.Blue)
    }
}
