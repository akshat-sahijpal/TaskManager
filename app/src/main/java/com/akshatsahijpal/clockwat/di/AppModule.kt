package com.akshatsahijpal.clockwat.di

import android.app.Application
import androidx.room.Room
import com.akshatsahijpal.clockwat.data.TodoDB
import com.akshatsahijpal.clockwat.repository.TodoRepository
import com.akshatsahijpal.clockwat.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): TodoDB =
        Room.databaseBuilder(app, TodoDB::class.java, "cp_db").build()

    @Provides
    @Singleton
    fun provideTodoRepository (db: TodoDB) : TodoRepository = TodoRepositoryImpl(db.dao)
}