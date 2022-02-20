package com.akshatsahijpal.clockwat.repository

import com.akshatsahijpal.clockwat.data.Todo
import com.akshatsahijpal.clockwat.util.UiEvents

interface TodoRepository {
    suspend fun insert(todo: Todo)
    suspend fun delete()
    suspend fun getTodoForName(userName: String): Todo?
    suspend fun getTodoForId(id: Int): Todo?
    fun getAllTodo(): kotlinx.coroutines.flow.Flow<List<Todo>>
}
