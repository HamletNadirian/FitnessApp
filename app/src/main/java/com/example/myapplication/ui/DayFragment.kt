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
        FragmentItem("1 - й День", "4 Упражнения", WorkoutFragment::class, 1),
        FragmentItem("2 - й День", "4 Упражнения", WorkoutFragment::class, 2),
        FragmentItem("3 - й День", "4 Упражнения", WorkoutFragment::class, 3),
        FragmentItem("4 - й День", "4 Упражнения", WorkoutFragment::class, 4),
        FragmentItem("5 - й День", "4 Упражнения", WorkoutFragment::class, 5),
        FragmentItem("6 - й День", "4 Упражнения", WorkoutFragment::class, 6),
        FragmentItem("7 - й День", "4 Упражнения", WorkoutFragment::class, 7),
        FragmentItem("8 - й День", "4 Упражнения", WorkoutFragment::class, 8),
        FragmentItem("9 - й День", "4 Упражнения", WorkoutFragment::class, 9),
        FragmentItem("10 - й День", "4 Упражнения", WorkoutFragment::class, 10),
        FragmentItem("11 - й День", "4 Упражнения", WorkoutFragment::class, 11),
        FragmentItem("12 - й День", "4 Упражнения", WorkoutFragment::class, 12),
        FragmentItem("13 - й День", "4 Упражнения", WorkoutFragment::class, 13),
        FragmentItem("14 - й День", "4 Упражнения", WorkoutFragment::class, 14)
    )

    // Исправлено: workoutId от 1 до 14 для второго уровня
    val items2 = listOf(
        FragmentItem("1 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 1),
        FragmentItem("2 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 2),
        FragmentItem("3 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 3),
        FragmentItem("4 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 4),
        FragmentItem("5 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 5),
        FragmentItem("6 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 6),
        FragmentItem("7 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 7),
        FragmentItem("8 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 8),
        FragmentItem("9 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 9),
        FragmentItem("10 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 10),
        FragmentItem("11 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 11),
        FragmentItem("12 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 12),
        FragmentItem("13 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 13),
        FragmentItem("14 - й День. Уровень №2", "4 Упражнения", WorkoutFragment::class, 14)
    )

    private val categories = listOf(
        WorkoutLevel(1, "1", "For the first level", R.drawable.first, items),
        WorkoutLevel(2, "2", "For the second level", R.drawable.second, items2)
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
                        adapter.updateItems(categories[pos].workouts, categories[pos].id)
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
        adapter.updateItems(categories.first().workouts, categories.first().id)
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