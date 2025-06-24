package com.example.myapplication.data

import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import com.example.myapplication.R
import com.example.myapplication.domain.WorkoutStateListener
import com.example.myapplication.domain.WorkoutViewState
import com.example.myapplication.exercises.AllExercises.workoutExercises

// WorkoutEngine.kt - ядро логики тренировки
class WorkoutEngine(private val workoutId: Int, private val workoutLvl: Int) : WorkoutController {

   private val exercises: List<Exercise> =
       workoutExercises[WorkoutKey( workoutId,workoutLvl)] ?: workoutExercises[WorkoutKey(1, 1)]!!

    private var currentIndex = 0
    private var remainingTime = 0
    private var isPaused = false
    private var timer: CountDownTimer? = null
    private var stateListener: WorkoutStateListener? = null

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

        startTimer(remainingTime)
        notifyStateChanged()
    }

    private fun startTimer(seconds: Int) {
        timer?.cancel()
        isPaused = false

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
                    startExercise()
                }
            }
        }.start()
    }

    private fun notifyStateChanged() {
        val currentExercise =
            if (currentIndex < exercises.size) exercises[currentIndex] else exercises[0]
        stateListener?.onWorkoutStateChanged(
            WorkoutViewState(
                exerciseName = currentExercise.name,
                gifResourceId = currentExercise.gifResId,
                timeRemaining = remainingTime,
                isPlaying = !isPaused,
                totalExercises = exercises.size,
                currentExerciseIndex = currentIndex + 1
            )
        )
    }

    override fun togglePause() {
        if (isPaused) {
            // Возобновление таймера
            startTimer(remainingTime)
        } else {
            // Пауза
            timer?.cancel()
            isPaused = true
            notifyStateChanged()
        }
    }

    override fun skipExercise() {
        timer?.cancel()
        remainingTime = 0
        currentIndex++
        if (currentIndex >= exercises.size) {
            stateListener?.onWorkoutComplete()
        } else {
            startExercise()
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

    fun cleanup() {
        timer?.cancel()
        stateListener = null
    }
}