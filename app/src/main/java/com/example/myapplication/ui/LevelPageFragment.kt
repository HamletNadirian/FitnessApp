package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.WorkoutApp
import com.example.myapplication.data.WorkoutLevel

class LevelPageFragment : Fragment() {
    private lateinit var workoutLevel: WorkoutLevel
    private lateinit var clickListener: OnItemClickListener
    private var adapter: CustomAdapter? = null
    private var levelNumber: Int = 1

    companion object {

        fun newInstance(
            workoutLevel: WorkoutLevel,
            clickListener: OnItemClickListener,
            levelNumber: Int = 1

        ): LevelPageFragment {
            val fragment = LevelPageFragment()
            fragment.workoutLevel = workoutLevel
            fragment.clickListener = clickListener
            fragment.levelNumber = levelNumber

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_level_page, container, false)

        // Настройка изображения уровня
        val levelImage = view.findViewById<ImageView>(R.id.levelImage)
        levelImage.setImageResource(workoutLevel.imageResId ?: R.drawable.first)

        // Настройка RecyclerView с тренировками
        val recyclerView = view.findViewById<RecyclerView>(R.id.workoutRecyclerView)
        val historyDao = (requireActivity().application as WorkoutApp).db.HistoryDao()

        adapter = CustomAdapter(workoutLevel.workouts, historyDao, this, clickListener)
        adapter?.updateCurrentLevel(levelNumber)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Добавляем snap для плавной прокрутки
        LinearSnapHelper().attachToRecyclerView(recyclerView)

        return view
    }

    override fun onResume() {
        super.onResume()
        // Обновляем уровень при возврате к фрагменту (на случай, если что-то изменилось)
        adapter?.updateCurrentLevel(levelNumber)
    }

}
