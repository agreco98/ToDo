package com.example.todo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo")
    fun getAllToDo(): Flow<List<ToDo>>

    @Query("SELECT * FROM todo WHERE :id")
    suspend fun getToDo(id: Long): ToDo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo : ToDo)

    @Delete
    suspend fun deleteToDo(toDo : ToDo)
}