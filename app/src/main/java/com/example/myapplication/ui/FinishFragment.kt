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
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.WorkoutApp
import com.example.myapplication.data.HistoryDao
import com.example.myapplication.data.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
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

        val tvCongratulations = view?.findViewById<TextView>(R.id.tvCongratulations)
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


        // Inflate the layout for this fragment
        return view
    }

    private fun saveCompletedWorkout()
        {
            val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val historyEntity = HistoryEntity(
                date = currentDate,
                workoutId = workoutId.toString(),
                workoutLvl = workoutLevel.toString() // Теперь используем правильный уровень
            )

            val historyDao = (requireActivity().application as WorkoutApp).db.HistoryDao()

            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    historyDao.insert(historyEntity)
                    Log.d(
                        "FinishFragment",
                        "Workout saved successfully: ID=$workoutId, Level=$workoutLevel, Date=$currentDate"
                    )
                    // Показываем Toast на главном потоке
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
        /*
            private fun addDatetoDatabase(historyDao: HistoryDao){

                val workoutId: Int = args.workoutId // У тебя он integer, а не String
                // Если нужно как строку:
                val workoutIdStr = workoutId.toString()
                val workoutLevel = args.workoutLvl
                Log.d("FinishFragment", "workoutIdStr: $workoutIdStr")

                val mycalender=Calendar.getInstance()
                val datetime=mycalender.time
                Log.e("Date",""+datetime)
                val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
                val currentdate=sdf.format(datetime)
                Log.e("Formated Date",""+currentdate)
                lifecycleScope.launch (Dispatchers.IO){
                    historyDao.insert(HistoryEntity(currentdate,workoutIdStr,workoutLevel))



                }
            }*/

}