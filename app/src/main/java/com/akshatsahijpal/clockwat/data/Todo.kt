package com.akshatsahijpal.clockwat.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var TaskName: String,
    var userName: String,
    var preference: Int,
    var isDone: Boolean,
    @PrimaryKey var id: Int = 1
)
