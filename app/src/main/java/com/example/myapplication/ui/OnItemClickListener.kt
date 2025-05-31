package com.example.myapplication.ui

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

interface OnItemClickListener {
    fun onItemClick(fragmentClass: KClass<out Fragment>, workoutId: Int)

}