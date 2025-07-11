package com.example.myapplication.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.WorkoutEngine

// Step 6: Update WorkoutViewModel to accept workout ID
class WorkoutViewModel(private val workoutId: Int, private val workoutLvl: Int) : ViewModel() {
    private val workoutEngine = WorkoutEngine(workoutId, workoutLvl)
    private val _navigateToFinish = MutableLiveData<Boolean>()
    val navigateToFinish: LiveData<Boolean> get() = _navigateToFinish

    fun completeWorkout() {
        _navigateToFinish.value = true
    }

    // Пример: вызов из логики
    fun checkIfFinished() {
        // Допустим, упражнения закончились:
        completeWorkout()
    }

    private val _viewState = MutableLiveData<WorkoutViewState>()
    val viewState: LiveData<WorkoutViewState> get() = _viewState

    init {
        workoutEngine.setStateListener(object : WorkoutStateListener {
            override fun onWorkoutStateChanged(state: WorkoutViewState) {
                _viewState.postValue(state)
            }

            override fun onWorkoutComplete() {
                completeWorkout()
            }
        })

        workoutEngine.start()
    }

    fun togglePause() {
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

    override fun onCleared() {
        super.onCleared()
        workoutEngine.cleanup()
    }
}