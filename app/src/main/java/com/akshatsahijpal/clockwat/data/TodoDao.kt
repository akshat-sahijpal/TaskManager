package com.akshatsahijpal.clockwat.data

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)
    @Delete
    suspend fun delete()
    @Query("SELECT * FROM todo WHERE userName = :userName")
    suspend fun getTodoForName(userName: String): Todo?
    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoForId(id: Int): Todo?
    @Query("SELECT * FROM todo")
    fun getAllTodo(): kotlinx.coroutines.flow.Flow<List<Todo>>
}