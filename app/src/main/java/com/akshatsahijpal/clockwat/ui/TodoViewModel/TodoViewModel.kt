package com.akshatsahijpal.clockwat.ui.TodoViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshatsahijpal.clockwat.data.Todo
import com.akshatsahijpal.clockwat.repository.TodoRepository
import com.akshatsahijpal.clockwat.util.Navigate
import com.akshatsahijpal.clockwat.util.Routes
import com.akshatsahijpal.clockwat.util.ShowSnackBar
import com.akshatsahijpal.clockwat.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private var repo: TodoRepository) : ViewModel() {
    private var _uiEvents = Channel<UiEvents>()
    var uiEvents: Flow<UiEvents> = _uiEvents.receiveAsFlow()
    var todos = repo.getAllTodo()
    private var recentlyDeletedTodo: Todo? = null
    fun onEvent(event: TodoEvents) {
        when (event) {
            is DeleteTodo -> {
                viewModelScope.launch {
                    recentlyDeletedTodo = event.todo
                    repo.delete()
                    _uiEvents.send(ShowSnackBar("Deleted!!!", "undo"))
                }
            }
            is OnTodoClick -> {
                handleEvents(Navigate(Routes.EDIT_TODO + "?id=${event.todo.id}"))
            }
            is OnUndoTodo -> {
                recentlyDeletedTodo?.let {
                    viewModelScope.launch {
                        repo.insert(it)
                    }
                }
            }
            is OnAddTodo -> {
                handleEvents(Navigate(Routes.EDIT_TODO))
            }
            is OnToggleDoneState -> {
                viewModelScope.launch {
                    repo.insert(event.todo.copy(isDone = event.state))
                }
            }
        }
    }

    private fun handleEvents(uiEv: UiEvents) {
        viewModelScope.launch {
            _uiEvents.send(uiEv)
        }
    }
}