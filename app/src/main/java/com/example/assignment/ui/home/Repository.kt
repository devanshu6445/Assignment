package com.example.assignment.ui.home

import com.example.assignment.database.AppDatabase
import com.example.assignment.database.ModelData

class Repository(private val database: AppDatabase) {

    suspend fun getAllData() = database.getDao().selectAll()

    suspend fun insertData(data:ModelData) = database.getDao().insert(data)
}