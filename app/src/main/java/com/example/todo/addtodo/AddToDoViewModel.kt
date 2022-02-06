package com.example.todo.addtodo

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.model.ToDo
import com.example.todo.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
    private val savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    private val _state = MutableStateFlow(AddToDoState())
    val state: StateFlow<AddToDoState>
        get () = _state

    private val title = MutableStateFlow("")
    private val description = MutableStateFlow("")

    private var currentId: Long = -1L

    init {
        viewModelScope.launch {
            combine(title, description) { text, description ->
                AddToDoState(text, description)
            }.collect {
                _state.value = it
            }
        }
    }

    init {
        idSelect()
    }

    private fun idSelect() {
        savedStateHandle.get<Long>("id")?.let { id ->
            if(id != -1L) {
                viewModelScope.launch {
                    toDoRepository.getToDo(id).also { toDo ->
                        currentId = toDo.id
                        title.value = toDo.title
                        description.value = toDo.description
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
        description.value = newDescription
    }

}
