package com.example.myapplication.data

import android.os.CountDownTimer
import com.example.myapplication.domain.WorkoutStateListener
import com.example.myapplication.domain.WorkoutViewState
import com.example.myapplication.exercises.AllExercises.workoutExercises

// WorkoutEngine.kt - ядро логики тренировки
class WorkoutEngine(private val workoutId: Int, private val workoutLvl: Int) : WorkoutController {

    private val exercises: List<Exercise> =
        workoutExercises[WorkoutKey(workoutId, workoutLvl)]
            ?: workoutExercises[WorkoutKey(1, 1)]!!

    private var currentIndex = 0
    private var remainingTime = 0
    private var isPaused = false
    private var timer: CountDownTimer? = null
    private var stateListener: WorkoutStateListener? = null
    private var isBackgroundPaused = false // Новое поле для отслеживания паузы из-за сворачивания

    fun setStateListener(listener: WorkoutStateListener) {
        this.stateListener = listener
        notifyStateChanged() // Отправка начального состояния
    }

    fun start() {
        if (currentIndex >= exercises.size) {
            currentIndex = 0
        }
        startExercise()
    }

    private fun startExercise() {
        if (currentIndex >= exercises.size) return

        val exercise = exercises[currentIndex]

        // Если это первый запуск упражнения, устанавливаем полное время
        if (remainingTime <= 0) {
            remainingTime = exercise.durationSeconds
        }

        isPaused = false
        startTimer(remainingTime)
        notifyStateChanged()
    }

    fun startNextExercise() {
        if (currentIndex < exercises.size) {
            // Сбрасываем remainingTime для нового упражнения
            remainingTime = 0
            startExercise()
        } else {
            // Если упражнения закончились
            stateListener?.onWorkoutComplete()
        }
    }

    private fun startTimer(seconds: Int) {
        timer?.cancel()
        // Таймер запускается только если не на паузе
        if (!isPaused && !isBackgroundPaused) {
            timer = object : CountDownTimer(seconds * 1000L, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    remainingTime = (millisUntilFinished / 1000).toInt()
                    notifyStateChanged()
                }

                override fun onFinish() {
                    remainingTime = 0
                    currentIndex++
                    if (currentIndex >= exercises.size) {
                        stateListener?.onWorkoutComplete()
                    } else {
                        // Запрашиваем отдых перед следующим упражнением
                        val nextExercise = exercises[currentIndex]
                        stateListener?.onRestRequired(nextExercise.name)
                    }
                }
            }.start()
        }
    }

private fun notifyStateChanged() {
    val currentExercise =
        if (currentIndex < exercises.size) exercises[currentIndex] else exercises[0]
    stateListener?.onWorkoutStateChanged(
        WorkoutViewState(
            exerciseName = currentExercise.name,
            gifResourceId = currentExercise.gifResId,
            timeRemaining = remainingTime,
            isPlaying = !isPaused && !isBackgroundPaused,
            totalExercises = exercises.size,
            currentExerciseIndex = currentIndex + 1,
            isResting = false
        )
    )
}

override fun togglePause() {
    if (isPaused) {
        startTimer(remainingTime)
    } else {
        timer?.cancel()
        isPaused = true
        notifyStateChanged()
    }
}

fun resumeWorkout() {
    if (isPaused && remainingTime > 0) {
        isPaused = false
        startTimer(remainingTime)
    }
}

override fun skipExercise() {
    timer?.cancel()
    remainingTime = 0
    currentIndex++
    if (currentIndex >= exercises.size) {
        stateListener?.onWorkoutComplete()
    } else {
        // Показываем rest screen перед следующим упражнением
        val nexExercise = exercises[currentIndex]
        stateListener?.onRestRequired(nexExercise.name)
    }
}

override fun previousExercise() {
    timer?.cancel()

    // Сброс паузы
    isPaused = false

    // Если мы в начале - просто перезапускаем текущее
    if (currentIndex == 0) {
        remainingTime = 0
        startExercise()
        return
    }

    // Переход к предыдущему
    currentIndex--
    remainingTime = 0
    startExercise()
}

fun pauseWorkout() {
    if (!isPaused) {
        timer?.cancel()
        isPaused = true
    }
}

fun cleanup() {
    timer?.cancel()
    stateListener = null
}
}