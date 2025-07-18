package com.example.myapplication.domain

interface WorkoutStateListener {
    fun onWorkoutStateChanged(state: WorkoutViewState)
    fun onWorkoutComplete() //
    fun onRestRequired(nextExerciseName: String) // Новый метод для запроса отдыха

}