package com.example.todo.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.model.ToDo
import com.example.todo.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {

    private val _state = MutableStateFlow(ToDoListState())
    val state: StateFlow<ToDoListState>
    get () = _state

    val allToDo = toDoRepository.getAllToDo()
    private val isFinished = MutableStateFlow(_state.value.isFinished)

    init {
        loadAllToDo()
    }

    private fun loadAllToDo() {
        viewModelScope.launch {
            combine(allToDo, isFinished) { allToDo, isFinished ->
                ToDoListState(allToDo, isFinished)
            }.collect {
                _state.value = it
            }
        }
    }

    fun updateIsFinished(isFinished: Boolean, id: Long) {
        viewModelScope.launch {
            toDoRepository.updateIsFinished(isFinished, id)
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.deleteToDo(toDo)
        }
    }

    fun getToDo(id: Long) {
        viewModelScope.launch {
            toDoRepository.getToDo(id)
        }
    }

}