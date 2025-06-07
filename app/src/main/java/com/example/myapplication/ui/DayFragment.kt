package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.WorkoutApp
import com.example.myapplication.data.WorkoutLevel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.reflect.KClass

class DayFragment : Fragment(), OnItemClickListener {
    private lateinit var categoryAdapter: LevelAdapter
    private lateinit var adapter: CustomAdapter

    // Добавляем переменную для отслеживания текущего уровня
    private var currentSelectedLevel: Int = 1

    val items = listOf(
        FragmentItem("1 - й День", "15 Упражнения", WorkoutFragment::class, 1),
        FragmentItem("2 - й День", "12 Упражнения", WorkoutFragment::class, 2),
        FragmentItem("3 - й День", "10 Упражнения", WorkoutFragment::class, 3)
    )
    val items2 = listOf(
        FragmentItem("1 - й День 2 Уровень", "15 Упражнения", WorkoutFragment::class, 1),//айди наверн другой?
        FragmentItem("2 - й День 2 Уровень", "12 Упражнения", WorkoutFragment::class, 2),
        FragmentItem("3 - й День 2 Уровень", "10 Упражнения", WorkoutFragment::class, 3)
    )
    private val categories = listOf(
        WorkoutLevel(1, "First Level", "For the first level", R.drawable.first, items),
        WorkoutLevel(2, "Second Level", "For the second level", R.drawable.second, items2)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view)

        val categoryRecycler = view.findViewById<RecyclerView>(R.id.recyclerCategories)
        categoryAdapter = LevelAdapter(categories)
        categoryRecycler?.layoutManager = LinearLayoutManager(requireContext())
        categoryRecycler?.adapter = categoryAdapter
        categoryRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        PagerSnapHelper().attachToRecyclerView(categoryRecycler)

        categoryRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val pos = layoutManager.findFirstCompletelyVisibleItemPosition()

                    if (pos != RecyclerView.NO_POSITION) {
                        currentSelectedLevel = categories[pos].id
                        adapter.updateItems(categories[pos].workouts,categories[pos].id)
                        // Передаем информацию о текущем уровне в адаптер
                        adapter.updateCurrentLevel(currentSelectedLevel)

                    }
                }
            }
        })


        val historyDao = (requireActivity().application as WorkoutApp).db.HistoryDao()

        adapter = CustomAdapter(items, historyDao, this, this)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Snap для продуктов
        LinearSnapHelper().attachToRecyclerView(recyclerView)

        // Показать товары первой категории
        adapter.updateItems(categories.first().workouts,categories.first().id)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.visibility = View.VISIBLE
    }

    override fun onItemClick(
        fragmentClass: KClass<out Fragment>,
        workoutId: Int
    ) {
        when (fragmentClass) {
            WorkoutFragment::class -> {
                // Создаём action с аргументом workoutId
                val action = DayFragmentDirections.actionDayFragmentToWorkoutPreviewFragment(workoutId, currentSelectedLevel)
                findNavController().navigate(action)
            }

            else -> {
                Toast.makeText(requireContext(), "Фрагмент не поддерживается", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}