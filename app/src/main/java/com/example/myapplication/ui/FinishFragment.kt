package com.example.myapplication.ui

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.WorkoutApp
import com.example.myapplication.data.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.Locale


class FinishFragment : Fragment() {
    private var workoutId: Int = 0
    private var workoutLevel: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = FinishFragmentArgs.fromBundle(requireArguments())
        workoutId = args.workoutId
        workoutLevel = args.workoutLvl

        Log.d("FinishFragment", "Received workoutId: $workoutId, workoutLevel: $workoutLevel")

        val view = inflater.inflate(R.layout.fragment_finish, container, false)

        view?.findViewById<TextView>(R.id.tvCongratulations)
        val tvWorkoutCompleted = view?.findViewById<TextView>(R.id.tvWorkoutCompleted)
        val btnBackToList = view?.findViewById<Button>(R.id.btnBackToList)

        // Устанавливаем сообщение о завершенной тренировке
        tvWorkoutCompleted?.text = "Тренировка успешно завершена!"
        saveCompletedWorkout()

        //addDatetoDatabase(historyDao)
        btnBackToList?.setOnClickListener {
            // Возвращаемся к списку тренировок
            try {
                val action = FinishFragmentDirections.actionFinishFragmentToDayFragment()

                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.dayFragment, true)
                    .build()

                findNavController().navigate(action, navOptions)
                Log.d("FinishFragment", "Выполнен переход")
            } catch (e: Exception) {
                Log.e("FinishFragment", "Ошибка навигации", e)
                // Альтернативный способ навигации
                findNavController().popBackStack(R.id.dayFragment, false)
            }
        }

        return view
    }

    private fun saveCompletedWorkout() {
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val currentTime =
            SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date()) // e.g., "01:15 PM"
        val weight = 9.0f // Example weight, could be user input

        val historyEntity = HistoryEntity(
            date = currentDate,
            time = currentTime,
            weight = weight,
            workoutId = workoutId.toString(),
            workoutLvl = workoutLevel.toString(),
            durationMinutes = 10.0f // Example duration, could be calculated based on workout exercises

        )

        val historyDao = (requireActivity().application as WorkoutApp).db.HistoryDao()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                historyDao.insert(historyEntity)
                Log.d(
                    "FinishFragment",
                    "Workout saved successfully: ID=$workoutId, Level=$workoutLevel, Date=$currentDate, Time=$currentTime, Weight=$weight"
                )
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Тренировка сохранена!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Log.e("FinishFragment", "Error saving workout", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Ошибка сохранения", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}