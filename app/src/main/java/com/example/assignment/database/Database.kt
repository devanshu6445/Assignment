package com.example.assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ModelData::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getDao():Dao

    companion object{
        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database").build()
    }
}