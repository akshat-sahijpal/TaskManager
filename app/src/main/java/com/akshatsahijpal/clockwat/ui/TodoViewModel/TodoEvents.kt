package com.akshatsahijpal.clockwat.ui.TodoViewModel

import com.akshatsahijpal.clockwat.data.Todo

sealed class TodoEvents
data class DeleteTodo (var todo:Todo): TodoEvents()
data class OnToggleDoneState (val todo:Todo, var state: Boolean): TodoEvents()
data class OnTodoClick (var todo: Todo): TodoEvents()
object OnUndoTodo: TodoEvents()
object OnAddTodo : TodoEvents()
data class OnEditTodo(var todo: Todo): TodoEvents()