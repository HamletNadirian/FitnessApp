package com.example.myapplication.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.example.myapplication.data.WorkoutLevel

class LevelPagerAdapter(
    fragment: Fragment,
    private val categories: List<WorkoutLevel>,
    private val clickListener: OnItemClickListener
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        val workoutLevel = categories[position]
        // Используем ID из WorkoutLevel как номер уровня
        val levelNumber = workoutLevel.id

        return LevelPageFragment.newInstance(
            workoutLevel,
            clickListener,
            levelNumber
        )
    }
}