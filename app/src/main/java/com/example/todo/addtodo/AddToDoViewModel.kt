package com.example.todo.addtodo

import kotlinx.coroutines.flow.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.addtodo.AddToDoState
import com.example.todo.data.model.ToDo
import com.example.todo.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {

    private val _state = MutableStateFlow(AddToDoState())
    val state: StateFlow<AddToDoState>
        get () = _state

    private val title = MutableStateFlow("")
    private val description = MutableStateFlow("")
    private val id = MutableStateFlow(-1L)

    init {
        loadAddToDo()
    }

    init {
        idSelect()
    }

    private fun loadAddToDo() {
        viewModelScope.launch {
            combine(title, description, id) { title, description, id ->
                AddToDoState(title, description, id)
            }.collect {
                _state.value = it
            }
        }
    }

    private fun idSelect() {
        viewModelScope.launch {
            toDoRepository.getAllToDo().collect { toDo ->
                toDo.find {
                    it.id == id.value
                }.also {
                    id.value = it?.id ?: -1L
                    if(id.value != -1L) {
                        title.value = it?.title ?: ""
                        description.value = it?.description ?: ""
                        id.value = it?.id ?: -1L
                    }
                }
            }
        }
    }

    fun insertToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.insertToDo(toDo)
        }
    }

    fun onTitleChange(newTitle: String) {
        title.value = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        title.value = newDescription
    }

}
