package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.data.WorkoutLevel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.reflect.KClass

class DayFragment : Fragment(), OnItemClickListener {
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: LevelPagerAdapter

    //  для отслеживания текущего уровня
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
        val view = inflater.inflate(R.layout.fragment_day_viewpager, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        pagerAdapter = LevelPagerAdapter(this, categories, this)
        viewPager.adapter = pagerAdapter

        // Слушатель для отслеживания смены страниц
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentSelectedLevel = categories[position].id
            }
        })

        currentSelectedLevel = categories.first().id
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