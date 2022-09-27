package com.example.assignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "yes_thats_me")
data class ModelData (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var code:Int = 0,
    @ColumnInfo(name = "logic")
    var text:String
        )