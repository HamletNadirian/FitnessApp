package com.example.myapplication.exercises

import com.example.myapplication.R
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.WorkoutKey

object AllExercises {
    val workoutExercises = mapOf(
        // Уровень 1
        WorkoutKey(1, 1) to listOf(
            Exercise("Dead bug", 3, R.drawable.deadbug),
            Exercise("Dumbbell-Curl", 3, R.drawable.dumbbellcurl),
            Exercise("Dumbbell-Lateral-Raise", 3, R.drawable.dumbbelllateralraise),
            Exercise("Lat-Pull down", 3, R.drawable.latpulldown)
        ),
        WorkoutKey(2, 1) to listOf(
            Exercise("Leg-Curl", 3, R.drawable.legurl),
            Exercise("LEG-EXTENSION", 3, R.drawable.legextension),
            Exercise("Lever-Shoulder-Press", 3, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly)
        ),
        WorkoutKey(3, 1) to listOf(
            Exercise("Lying-Chest-Press-Machine", 3, R.drawable.lyingchestpressmachine),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly),
            Exercise("Pushdown", 3, R.drawable.pushdown),
            Exercise("Lat-Pull down", 3, R.drawable.latpulldown)
        ),
        WorkoutKey(4, 1) to listOf(
            Exercise("Seated-Row-Machine", 3, R.drawable.seatedrowmachine),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly),
            Exercise("Gluteal bridge", 3, R.drawable.gluteal_bridge),
            Exercise("LEG-EXTENSION", 3, R.drawable.legextension)
        ),
        WorkoutKey(5, 1) to listOf(
            Exercise("Dead bug", 3, R.drawable.deadbug),
            Exercise("Pushdown", 3, R.drawable.pushdown),
            Exercise("Dumbbell-Curl", 3, R.drawable.dumbbellcurl),
            Exercise("LEG-EXTENSION", 3, R.drawable.legextension)
        ),
        WorkoutKey(6, 1) to listOf(
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly),
            Exercise("Leg-Curl", 3, R.drawable.legurl),
            Exercise("Dead bug", 3, R.drawable.deadbug),
            Exercise("Pushdown", 3, R.drawable.pushdown)
        ),
        WorkoutKey(7, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 3, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 3, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 3, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly)
        ),
        WorkoutKey(8, 1) to listOf(
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly),
            Exercise("Dumbbell-Lateral-Raise", 3, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 3, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 3, R.drawable.levershoulderpress)
        ),
        WorkoutKey(9, 1) to listOf(
            Exercise("Lat-Pull down", 3, R.drawable.latpulldown),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly),
            Exercise("Dumbbell-Lateral-Raise", 3, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 3, R.drawable.lyingchestpressmachine)
        ),
        WorkoutKey(10, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 3, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 3, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 3, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly)
        ),
        WorkoutKey(11, 1) to listOf(
            Exercise("Dead bug", 3, R.drawable.deadbug),
            Exercise("Pushdown", 3, R.drawable.pushdown),
            Exercise("Dumbbell-Curl", 3, R.drawable.dumbbellcurl),
            Exercise("LEG-EXTENSION", 3, R.drawable.legextension)
        ),
        WorkoutKey(12, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 3, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 3, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 3, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly)
        ),
        WorkoutKey(13, 1) to listOf(
            Exercise("Leg-Curl", 3, R.drawable.legurl),
            Exercise("Dead bug", 3, R.drawable.deadbug),
            Exercise("Pushdown", 3, R.drawable.pushdown),
            Exercise("Dumbbell-Curl", 3, R.drawable.dumbbellcurl)
        ),
        WorkoutKey(14, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 3, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 3, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 3, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 3, R.drawable.pecdeckfly)
        ),

        // Уровень 2 - исправленные ключи
        WorkoutKey(1, 2) to listOf(
            Exercise("Dead bug", 6, R.drawable.deadbug),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl),
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise),
            Exercise("Lat-Pull down", 6, R.drawable.latpulldown)
        ),
        WorkoutKey(2, 2) to listOf(
            Exercise("Leg-Curl", 6, R.drawable.legurl),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly)
        ),
        WorkoutKey(3, 2) to listOf(
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly),
            Exercise("Pushdown", 6, R.drawable.pushdown),
            Exercise("Lat-Pull down", 6, R.drawable.latpulldown)
        ),
        WorkoutKey(4, 2) to listOf(
            Exercise("Seated-Row-Machine", 6, R.drawable.seatedrowmachine),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly),
            Exercise("Gluteal bridge", 6, R.drawable.gluteal_bridge),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension)
        ),
        WorkoutKey(5, 2) to listOf(
            Exercise("Dead bug", 6, R.drawable.deadbug),
            Exercise("Pushdown", 6, R.drawable.pushdown),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension)
        ),
        WorkoutKey(6, 2) to listOf(
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly),
            Exercise("Leg-Curl", 6, R.drawable.legurl),
            Exercise("Dead bug", 6, R.drawable.deadbug),
            Exercise("Pushdown", 6, R.drawable.pushdown)
        ),
        WorkoutKey(7, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly)
        ),
        WorkoutKey(8, 2) to listOf(
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly),
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress)
        ),
        WorkoutKey(9, 2) to listOf(
            Exercise("Lat-Pull down", 6, R.drawable.latpulldown),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly),
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine)
        ),
        WorkoutKey(10, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly)
        ),
        WorkoutKey(11, 2) to listOf(
            Exercise("Dead bug", 6, R.drawable.deadbug),
            Exercise("Pushdown", 6, R.drawable.pushdown),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension)
        ),
        WorkoutKey(12, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly)
        ),
        WorkoutKey(13, 2) to listOf(
            Exercise("Leg-Curl", 6, R.drawable.legurl),
            Exercise("Dead bug", 6, R.drawable.deadbug),
            Exercise("Pushdown", 6, R.drawable.pushdown),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl)
        ),
        WorkoutKey(14, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly)
        )
    )

    fun getExercisesForWorkout(workoutId: Int, workoutLvl: Int): List<Exercise> {
        return workoutExercises[WorkoutKey(workoutId, workoutLvl)]
            ?: workoutExercises[WorkoutKey(1, 1)]!!
    }
}