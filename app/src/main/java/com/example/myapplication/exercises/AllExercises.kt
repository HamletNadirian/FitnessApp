package com.example.myapplication.exercises

import com.example.myapplication.R
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.WorkoutKey

object AllExercises {

    var duration_ex = 300
    val workoutExercises: Map<WorkoutKey, List<Exercise>> = mapOf(
        WorkoutKey(1, 1) to listOf(
            Exercise("Dead bug", 30, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Dumbbell-Curl", 30, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body."),
            Exercise("Dumbbell-Lateral-Raise", 30, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lat-Pull down", 30, R.drawable.latpulldown, "Pull bar down to chest while seated, engaging back muscles, keeping core tight.")
        ),
        WorkoutKey(2, 1) to listOf(
            Exercise("Leg-Curl", 30, R.drawable.legurl, "Lie face down on machine, curl legs toward glutes, contracting hamstrings."),
            Exercise("LEG-EXTENSION", 30, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps."),
            Exercise("Lever-Shoulder-Press", 30, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(30, 1) to listOf(
            Exercise("Lying-Chest-Press-Machine", 30, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Pushdown", 30, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Lat-Pull down", 30, R.drawable.latpulldown, "Pull bar down to chest while seated, engaging back muscles, keeping core tight.")
        ),
        WorkoutKey(4, 1) to listOf(
            Exercise("Seated-Row-Machine", 30, R.drawable.seatedrowmachine, "Sit on machine, pull handles toward torso, engaging back and biceps."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Gluteal bridge", 30, R.drawable.gluteal_bridge, "Lie on back, knees bent, lift hips toward ceiling, engaging glutes."),
            Exercise("LEG-EXTENSION", 30, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps.")
        ),
        WorkoutKey(5, 1) to listOf(
            Exercise("Dead bug", 30, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 30, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Dumbbell-Curl", 30, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body."),
            Exercise("LEG-EXTENSION", 30, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps.")
        ),
        WorkoutKey(6, 1) to listOf(
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Leg-Curl", 30, R.drawable.legurl, "Lie face down on machine, curl legs toward glutes, contracting hamstrings."),
            Exercise("Dead bug", 30, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 30, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps.")
        ),
        WorkoutKey(7, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 30, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 30, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 30, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(8, 1) to listOf(
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Dumbbell-Lateral-Raise", 30, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 30, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 30, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids.")
        ),
        WorkoutKey(9, 1) to listOf(
            Exercise("Lat-Pull down", 30, R.drawable.latpulldown, "Pull bar down to chest while seated, engaging back muscles, keeping core tight."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Dumbbell-Lateral-Raise", 30, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 30, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps.")
        ),
        WorkoutKey(10, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 30, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 30, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 30, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(11, 1) to listOf(
            Exercise("Dead bug", 30, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 30, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Dumbbell-Curl", 30, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body."),
            Exercise("LEG-EXTENSION", 30, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps.")
        ),
        WorkoutKey(12, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 30, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 30, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 30, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(130, 1) to listOf(
            Exercise("Leg-Curl", 30, R.drawable.legurl, "Lie face down on machine, curl legs toward glutes, contracting hamstrings."),
            Exercise("Dead bug", 30, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 30, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Dumbbell-Curl", 30, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body.")
        ),
        WorkoutKey(14, 1) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 30, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 30, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 30, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 30, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        // Level 2
        WorkoutKey(1, 2) to listOf(
            Exercise("Dead bug", 6, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body."),
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lat-Pull down", 6, R.drawable.latpulldown, "Pull bar down to chest while seated, engaging back muscles, keeping core tight.")
        ),
        WorkoutKey(2, 2) to listOf(
            Exercise("Leg-Curl", 6, R.drawable.legurl, "Lie face down on machine, curl legs toward glutes, contracting hamstrings."),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps."),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(30, 2) to listOf(
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Pushdown", 6, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Lat-Pull down", 6, R.drawable.latpulldown, "Pull bar down to chest while seated, engaging back muscles, keeping core tight.")
        ),
        WorkoutKey(4, 2) to listOf(
            Exercise("Seated-Row-Machine", 6, R.drawable.seatedrowmachine, "Sit on machine, pull handles toward torso, engaging back and biceps."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Gluteal bridge", 6, R.drawable.gluteal_bridge, "Lie on back, knees bent, lift hips toward ceiling, engaging glutes."),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps.")
        ),
        WorkoutKey(5, 2) to listOf(
            Exercise("Dead bug", 6, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 6, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body."),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps.")
        ),
        WorkoutKey(6, 2) to listOf(
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Leg-Curl", 6, R.drawable.legurl, "Lie face down on machine, curl legs toward glutes, contracting hamstrings."),
            Exercise("Dead bug", 6, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 6, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps.")
        ),
        WorkoutKey(7, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(8, 2) to listOf(
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids.")
        ),
        WorkoutKey(9, 2) to listOf(
            Exercise("Lat-Pull down", 6, R.drawable.latpulldown, "Pull bar down to chest while seated, engaging back muscles, keeping core tight."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals."),
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps.")
        ),
        WorkoutKey(10, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(11, 2) to listOf(
            Exercise("Dead bug", 6, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 6, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body."),
            Exercise("LEG-EXTENSION", 6, R.drawable.legextension, "Sit on machine, extend legs to straighten knees, engaging quadriceps.")
        ),
        WorkoutKey(12, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        ),
        WorkoutKey(130, 2) to listOf(
            Exercise("Leg-Curl", 6, R.drawable.legurl, "Lie face down on machine, curl legs toward glutes, contracting hamstrings."),
            Exercise("Dead bug", 6, R.drawable.deadbug, "Lie on your back, lift arms and legs, and alternate extending opposite arm and leg while keeping core engaged."),
            Exercise("Pushdown", 6, R.drawable.pushdown, "Stand at cable machine, push bar down to thighs, engaging triceps."),
            Exercise("Dumbbell-Curl", 6, R.drawable.dumbbellcurl, "Stand with dumbbells in hands, curl weights toward shoulders, keeping elbows close to body.")
        ),
        WorkoutKey(14, 2) to listOf(
            Exercise("Dumbbell-Lateral-Raise", 6, R.drawable.dumbbelllateralraise, "Hold dumbbells at sides, raise arms to shoulder height, keeping elbows slightly bent."),
            Exercise("Lying-Chest-Press-Machine", 6, R.drawable.lyingchestpressmachine, "Lie on machine, push handles upward, engaging chest and triceps."),
            Exercise("Lever-Shoulder-Press", 6, R.drawable.levershoulderpress, "Push handles upward from shoulder height while seated, engaging deltoids."),
            Exercise("Pec-Deck-Fly", 6, R.drawable.pecdeckfly, "Sit on machine, bring handles together in front of chest, engaging pectorals.")
        )
    )

    fun getExercisesForWorkout(workoutId: Int, workoutLvl: Int): List<Exercise> {
        return workoutExercises[WorkoutKey(workoutId, workoutLvl)]
            ?: workoutExercises[WorkoutKey(1, 1)]!!
    }
}