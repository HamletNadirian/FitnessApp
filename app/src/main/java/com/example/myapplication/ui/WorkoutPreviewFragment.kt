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
import com.example.myapplication.data.Exercise
import com.example.myapplication.exercises.AllExercises
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.myapplication.ui.OnItemClickListener // <= важно!
import kotlin.reflect.KClass

class WorkoutPreviewFragment : Fragment() {

    private val args: WorkoutPreviewFragmentArgs by navArgs()
    private lateinit var exercisePreviewAdapter: ExercisePreviewAdapter
    private lateinit var btnContinue: Button

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

     fun setupUI(view: View) {
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
      //  exercisePreviewAdapter = ExercisePreviewAdapter(exercises)

     exercisePreviewAdapter = ExercisePreviewAdapter(exercises, object : OnItemClickListener {
         override fun onItemClick(
             fragmentClass: KClass<out Fragment>,
             workoutId: Int
         ) {
             TODO("Not yet implemented")
         }

         override fun onExerciseClick(exercise: Exercise) {
             // Обработка клика на упражнение
             val bottomSheet = ExerciseDetailBottomSheet.newInstance(exercise)
             bottomSheet.show(childFragmentManager, "exercise_detail_bottom_sheet")
         }
     })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = exercisePreviewAdapter

        btnContinue = view.findViewById(R.id.btnStartExercises)

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