package com.example.myapplication.data

data class RestState(
    val isResting: Boolean = false,
    val restTimeRemaining: Int = 0,
    val nextExerciseName: String = ""
)
