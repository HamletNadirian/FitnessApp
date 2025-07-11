package com.example.myapplication.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.domain.WorkoutViewModel
import com.example.myapplication.domain.WorkoutViewModelFactory
import com.example.myapplication.domain.WorkoutViewState
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

class WorkoutFragment : Fragment() {

    private var workoutId: Int = 0
    private var workoutLevel: Int = 1
    private lateinit var viewModel: WorkoutViewModel
    private lateinit var imageView: ImageView
    private lateinit var textExerciseName: TextView
    private lateinit var textTimer: TextView
    private lateinit var textProgress: TextView // Added to show progress
    private lateinit var buttonPause: ImageView
    private lateinit var buttonSkip: ImageView
    private lateinit var buttonPrev: ImageView
    private var tts: TextToSpeech? = null
    private var lastSpokenExerciseName: String? = null
    private var isTtsReady = false


    private lateinit var progressCircular: CircularProgressIndicator
    private lateinit var textTimerCenter: TextView
    private lateinit var restScreen: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            workoutId = it.getInt("workoutId", 0)
            workoutLevel = it.getInt("workout_lvl", 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = WorkoutFragmentArgs.fromBundle(requireArguments())
        workoutId = args.workoutId
        workoutLevel = args.workoutLvl

        return inflater.inflate(R.layout.fragment_workout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restScreen = view.findViewById(R.id.restScreenContainer)

        // Use custom ViewModelFactory to create ViewModel with workoutId
        val factory = WorkoutViewModelFactory(workoutId, workoutLevel)
        viewModel = ViewModelProvider(this, factory)[WorkoutViewModel::class.java]

        // Инициализация UI компонентов
        imageView = view.findViewById(R.id.imageAnimation)
        textExerciseName = view.findViewById(R.id.textExerciseName)
        textTimer = view.findViewById(R.id.textTimer)
        textProgress = view.findViewById(R.id.textProgress)
        buttonPause = view.findViewById(R.id.buttonPause)
        buttonSkip = view.findViewById(R.id.buttonSkip)
        buttonPrev = view.findViewById(R.id.buttonPrev)


        progressCircular = view.findViewById(R.id.progressCircular)
        textTimerCenter = view.findViewById(R.id.textTimerCenter)



        // Настройка UI контроллеров
        setupControllers()

        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.ENGLISH)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported")
                } else {
                    Log.d("TTS", "TTS initialized successfully")
                    isTtsReady = true
                    // Озвучить первое упражнение, если оно уже загружено

                    viewModel.viewState.value?.let { state ->
                        if (state.exerciseName.isNotEmpty() && lastSpokenExerciseName == null) {

                            speakOut(state.exerciseName)
                            lastSpokenExerciseName = state.exerciseName
                        }
                    }
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }

        // Наблюдение за состоянием тренировки
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            if (state.isResting) {
                showRestScreenAndStartExercise {
                    viewModel.startNextExercise() // Добавьте этот метод в ViewModel
                }
            } else {
                hideRestScreen()
            }
            updateUI(state)
        }
        viewModel.navigateToFinish.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                val action = WorkoutFragmentDirections.actionWorkoutFragmentToFinishFragment(
                    workoutId,
                    workoutLevel
                )
                findNavController().navigate(action)
            }
        }
    }
    // Исправленный метод для showRestScreenAndStartExercise
    private fun showRestScreenAndStartExercise(onFinish: () -> Unit) {
        restScreen.visibility = View.VISIBLE
        // Устанавливаем максимальное значение прогресс-бара
        progressCircular.max = 10
        progressCircular.progress = 10


        lifecycleScope.launch {
            for (time in 10 downTo 1) {
                progressCircular.progress = time
                textTimerCenter.text = time.toString()
                delay(1000)
            }
            progressCircular.progress = 0
            textTimerCenter.text = "0"
            delay(100)

            restScreen.visibility = View.GONE
            onFinish()
        }
    }
    private fun speakOut(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts?.stop()
        tts?.shutdown()
        tts = null
    }

    private fun setupControllers() {
        buttonPause.setOnClickListener {
            viewModel.togglePause()
        }

        buttonSkip.setOnClickListener {
            viewModel.skip()
        }

        buttonPrev.setOnClickListener {
            viewModel.prev()
        }
    }
    private fun hideRestScreen() {
        restScreen.visibility = View.GONE
    }
    private fun updateUI(state: WorkoutViewState) {

        if (state.exerciseName != lastSpokenExerciseName && state.exerciseName.isNotEmpty() && isTtsReady) {
            speakOut(state.exerciseName)
            lastSpokenExerciseName = state.exerciseName
        }
        // Обновление названия упражнения
        textExerciseName.text = state.exerciseName

        // Обновление GIF анимации
        Glide.with(requireContext())
            .asGif()
            .load(state.gifResourceId)
            .into(imageView)

        // Обновление таймера
        textTimer.text = String.format("00:%02d", state.timeRemaining)

        // Show progress (e.g., "Exercise 1/3")
        textProgress.text = "Упражнение ${state.currentExerciseIndex}/${state.totalExercises}"

        // Обновление кнопки паузы/воспроизведения
        buttonPause.setImageResource(
            if (state.isPlaying) R.drawable.pause_circle_svgrepo_com
            else R.drawable.play_circle_svgrepo_com
        )
    }
}