package com.example.todo.repository

import com.example.todo.data.ToDoDao
import com.example.todo.data.model.ToDo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val dao: ToDoDao) {

    fun getAllToDo(): Flow<List<ToDo>> = dao.getAllToDo()

    suspend fun getToDo(id: Long): ToDo = dao.getToDo(id)

    suspend fun updateIsFinished(isFinished: Boolean, id: Long) = dao.updateIsFinished(isFinished, id)

    suspend fun insertToDo(toDo: ToDo) = dao.insertToDo(toDo)

    suspend fun deleteToDo(toDo: ToDo) = dao.deleteToDo(toDo)

}