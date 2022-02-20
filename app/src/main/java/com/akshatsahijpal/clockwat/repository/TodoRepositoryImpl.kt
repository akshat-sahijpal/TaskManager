package com.akshatsahijpal.clockwat.repository

import com.akshatsahijpal.clockwat.data.Todo
import com.akshatsahijpal.clockwat.data.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl constructor(private var dao: TodoDao) : TodoRepository {
    override suspend fun insert(todo: Todo) {
        dao.insert(todo)
    }

    override suspend fun delete() {
        dao.delete()
    }

    override suspend fun getTodoForName(userName: String): Todo? = dao.getTodoForName(userName = userName)

    override suspend fun getTodoForId(id: Int): Todo? = dao.getTodoForId(id)


    override fun getAllTodo(): Flow<List<Todo>> = dao.getAllTodo()
}