package com.example.myapplication.domain

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.RestState
import com.example.myapplication.data.WorkoutEngine

class WorkoutViewModel(private val workoutId: Int, private val workoutLvl: Int) : ViewModel() {
    private val workoutEngine = WorkoutEngine(workoutId, workoutLvl)

    private val _navigateToFinish = MutableLiveData<Boolean>()
    val navigateToFinish: LiveData<Boolean> get() = _navigateToFinish

    private val _viewState = MutableLiveData<WorkoutViewState>()
    val viewState: LiveData<WorkoutViewState> get() = _viewState

    private val _restState = MutableLiveData<RestState>()
    val restState: LiveData<RestState> get() = _restState

    // Для управления таймером отдыха
    private var restTimer: CountDownTimer? = null
    private var remainingRestTime = 0
    private var isRestPaused = false
    private val REST_DURATION = 10 // секунды
    var isUserPaused = false // Новая переменная для отслеживания пользовательской паузы
    init {
        workoutEngine.setStateListener(object : WorkoutStateListener {
            override fun onWorkoutStateChanged(state: WorkoutViewState) {
                _viewState.postValue(state)
            }

            override fun onWorkoutComplete() {
                completeWorkout()
            }

            override fun onRestRequired(nextExerciseName: String) {
                startRestScreen(nextExerciseName)
            }
        })

        // Инициализируем состояние отдыха
        _restState.value = RestState()
        workoutEngine.start()
    }

    private fun startRestScreen(nextExerciseName: String) {
        remainingRestTime = REST_DURATION
        isRestPaused = false

        _restState.value = RestState(
            isResting = true,
            restTimeRemaining = remainingRestTime,
            nextExerciseName = nextExerciseName
        )

        startRestTimer()
    }

    private fun startRestTimer() {
        restTimer?.cancel()

        restTimer = object : CountDownTimer(remainingRestTime * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingRestTime = (millisUntilFinished / 1000).toInt()
                _restState.value = _restState.value?.copy(
                    restTimeRemaining = remainingRestTime
                )
            }

            override fun onFinish() {
                remainingRestTime = 0
                _restState.value = RestState(isResting = false)
                workoutEngine.startNextExercise()
            }
        }.start()
    }

    // Методы для управления отдыхом
    fun pauseRest() {
        if (!isRestPaused) {
            restTimer?.cancel()
            isRestPaused = true
        }
    }

    fun resumeRest() {
        if (isRestPaused && remainingRestTime > 0) {
            isRestPaused = false
            startRestTimer()
        }
    }
    fun pauseForBackground() {
        if (!isUserPaused) { // Пауза только если пользователь не поставил на паузу
            workoutEngine.pauseForBackground()
            pauseRest()
        }
    }

    fun resumeForBackground() {
        if (!isUserPaused) { // Возобновляем только если не на паузе от пользователя
            workoutEngine.resumeWorkout()
            resumeRest()
        }
    }


    // Методы для управления всей тренировкой при сворачивании
    fun pauseWorkout() {
        isUserPaused = true
        // Останавливаем основной таймер тренировки
        workoutEngine.pauseWorkout()
        // Останавливаем отдых
        pauseRest()
    }

    fun resumeWorkout() {
        isUserPaused = false
        // Возобновляем основной таймер тренировки
        workoutEngine.resumeWorkout()
        // Возобновляем отдых
        resumeRest()
    }

    fun skipRest() {
        restTimer?.cancel()
        _restState.value = RestState(isResting = false)
        workoutEngine.startNextExercise()
    }

    // Методы для управления тренировкой
    fun togglePause() {
        isUserPaused = !isUserPaused
        workoutEngine.togglePause()
    }

    fun skip() {
        workoutEngine.skipExercise()
    }

    fun startNextExercise() {
        workoutEngine.startNextExercise()
    }

    fun prev() {
        workoutEngine.previousExercise()
    }

    private fun completeWorkout() {
        _navigateToFinish.value = true
    }

    override fun onCleared() {
        super.onCleared()
        restTimer?.cancel()
        workoutEngine.cleanup()
    }
}