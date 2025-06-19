package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.exercises.AllExercises
import com.google.android.material.bottomnavigation.BottomNavigationView

class WorkoutPreviewFragment : Fragment() {

    private val args: WorkoutPreviewFragmentArgs by navArgs()
    private lateinit var exercisePreviewAdapter: ExercisePreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Скрываем bottom navigation
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.visibility = View.GONE

        setupUI(view)
    }

    private fun setupUI(view: View) {
        val workoutId = args.workoutId
        val workoutLevel = args.workoutLevel
        // Получаем упражнения из WorkoutEngine
        val exercises = AllExercises.getExercisesForWorkout(workoutId, workoutLevel)

        // Настройка заголовка
        val titleText = view.findViewById<TextView>(R.id.tvWorkoutTitle)
        val levelBadge = view.findViewById<TextView>(R.id.tvLevelBadge)
        val exerciseCount = view.findViewById<TextView>(R.id.tvExerciseCount)
        val totalTime = view.findViewById<TextView>(R.id.tvTotalTime)

        titleText.text = "${workoutId}-й День"

        when (workoutLevel) {
            1 -> levelBadge.text = "⚡ Новичок"
            2 -> levelBadge.text = "🔥 Продвинутый"
            else -> levelBadge.text = "⭐ Уровень $workoutLevel"
        }

        exerciseCount.text = "${exercises.size}\nУпражнения"

        val totalSeconds = exercises.sumOf { it.durationSeconds }
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        totalTime.text = "${minutes} Минут. ${seconds} Секунд. \nВремя"

        // Настройка RecyclerView с упражнениями
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerExercises)
        exercisePreviewAdapter = ExercisePreviewAdapter(exercises)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = exercisePreviewAdapter

        // Кнопки
      //  val btnStartAgain = view.findViewById<Button>(R.id.btnStartAgain)
        val btnContinue = view.findViewById<Button>(R.id.btnStartExercises)

      /*  btnStartAgain.setOnClickListener {
            // Переход к тренировке с самого начала
            val action = WorkoutPreviewFragmentDirections
                .actionWorkoutPreviewFragmentToWorkoutFragment(workoutId, workoutLevel)
            findNavController().navigate(action)
        }*/

        btnContinue.setOnClickListener {
            // Здесь можно добавить логику для продолжения с места остановки
            // Пока что просто переходим к тренировке
            val action = WorkoutPreviewFragmentDirections
                .actionWorkoutPreviewFragmentToWorkoutFragment(workoutId, workoutLevel)
            findNavController().navigate(action)
        }

        // Кнопка назад
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}