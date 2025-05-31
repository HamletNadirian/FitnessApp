package com.example.myapplication.data

import com.example.myapplication.ui.FragmentItem

data class WorkoutLevel(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int? = null,
    val workouts: List<FragmentItem>
)
