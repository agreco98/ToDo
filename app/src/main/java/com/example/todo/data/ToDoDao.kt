package com.example.todo.data

import androidx.room.*
import com.example.todo.data.model.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo")
    fun getAllToDo(): Flow<List<ToDo>>

    @Query("SELECT * FROM todo WHERE :id")
    suspend fun getToDo(id: Long): ToDo

    @Query("UPDATE todo SET isFinished = :isFinished WHERE :id")
    suspend fun updateIsFinished(isFinished: Boolean, id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo : ToDo)

    @Delete
    suspend fun deleteToDo(toDo : ToDo)
}