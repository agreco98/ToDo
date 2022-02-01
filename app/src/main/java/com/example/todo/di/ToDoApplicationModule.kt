package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.data.ToDoDao
import com.example.todo.data.database.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object ToDoApplicationModule {

    @Provides
    @Singleton
    fun providesToDoDatabase(application : Application) : ToDoDatabase {
        return Room.databaseBuilder(
            application,
            ToDoDatabase::class.java,
            "TodoDataBase"
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesDao(db: ToDoDatabase) : ToDoDao {
        return db.getDao()
    }
}