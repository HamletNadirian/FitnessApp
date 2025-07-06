package com.example.myapplication.ui

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

data class FragmentItem(
    val numberDay: String,
    val countExercise: String,
    val fragmentClass: KClass<out Fragment>,
    var workoutId: Int,

    )