package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history-table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val time: String,
    val weight: Float,
    val durationMinutes: Float,
    @ColumnInfo(name = "workout_id")
    val workoutId: String,
    @ColumnInfo(name = "workout_lvl")
    val workoutLvl: String
)

