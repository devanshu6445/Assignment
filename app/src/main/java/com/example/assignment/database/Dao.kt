package com.example.assignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(modelData: ModelData)

    @Query("select * from yes_thats_me")
    fun selectAll():Flow<List<ModelData>>
}