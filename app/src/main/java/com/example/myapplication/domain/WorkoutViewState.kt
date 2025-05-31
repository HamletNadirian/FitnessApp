package com.example.myapplication.domain

data class WorkoutViewState(
    val exerciseName: String,
    val gifResourceId: Int,
    val timeRemaining: Int,
    val isPlaying: Boolean,
    val totalExercises: Int,
    val currentExerciseIndex: Int
)