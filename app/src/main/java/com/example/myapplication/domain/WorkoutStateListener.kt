package com.example.myapplication.domain

interface WorkoutStateListener {
    fun onWorkoutStateChanged(state: WorkoutViewState)
    fun onWorkoutComplete() // ← добавь этот метод

}