package com.akshatsahijpal.clockwat.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Todo::class], version = 1)
abstract class TodoDB : RoomDatabase() {
    abstract var dao: TodoDao
}