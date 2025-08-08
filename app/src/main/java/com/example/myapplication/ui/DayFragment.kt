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
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.WorkoutLevel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kotlin.reflect.KClass


class DayFragment : Fragment(), OnItemClickListener {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: LevelPagerAdapter

    //  для отслеживания текущего уровня
    private var currentSelectedLevel: Int = 1

    val items = listOf(
        FragmentItem("1st Day", "4 Exercises", WorkoutFragment::class, 1),
        FragmentItem("2nd Day", "4 Exercises", WorkoutFragment::class, 2),
        FragmentItem("3rd Day", "4 Exercises", WorkoutFragment::class, 3),
        FragmentItem("4th Day", "4 Exercises", WorkoutFragment::class, 4),
        FragmentItem("5th Day", "4 Exercises", WorkoutFragment::class, 5),
        FragmentItem("6th Day", "4 Exercises", WorkoutFragment::class, 6),
        FragmentItem("7th Day", "4 Exercises", WorkoutFragment::class, 7),
        FragmentItem("8th Day", "4 Exercises", WorkoutFragment::class, 8),
        FragmentItem("9th Day", "4 Exercises", WorkoutFragment::class, 9),
        FragmentItem("10th Day", "4 Exercises", WorkoutFragment::class, 10),
        FragmentItem("11th Day", "4 Exercises", WorkoutFragment::class, 11),
        FragmentItem("12th Day", "4 Exercises", WorkoutFragment::class, 12),
        FragmentItem("13th Day", "4 Exercises", WorkoutFragment::class, 13),
        FragmentItem("14th Day", "4 Exercises", WorkoutFragment::class, 14)
    )

    val items2 = listOf(
        FragmentItem("1st Day. Level №2", "4 Exercises", WorkoutFragment::class, 1),
        FragmentItem("2nd Day. Level №2", "4 Exercises", WorkoutFragment::class, 2),
        FragmentItem("3rd Day. Level №2", "4 Exercises", WorkoutFragment::class, 3),
        FragmentItem("4th Day. Level №2", "4 Exercises", WorkoutFragment::class, 4),
        FragmentItem("5th Day. Level №2", "4 Exercises", WorkoutFragment::class, 5),
        FragmentItem("6th Day. Level №2", "4 Exercises", WorkoutFragment::class, 6),
        FragmentItem("7th Day. Level №2", "4 Exercises", WorkoutFragment::class, 7),
        FragmentItem("8th Day. Level №2", "4 Exercises", WorkoutFragment::class, 8),
        FragmentItem("9th Day. Level №2", "4 Exercises", WorkoutFragment::class, 9),
        FragmentItem("10th Day. Level №2", "4 Exercises", WorkoutFragment::class, 10),
        FragmentItem("11th Day. Level №2", "4 Exercises", WorkoutFragment::class, 11),
        FragmentItem("12th Day. Level №2", "4 Exercises", WorkoutFragment::class, 12),
        FragmentItem("13th Day. Level №2", "4 Exercises", WorkoutFragment::class, 13),
        FragmentItem("14th Day. Level №2", "4 Exercises", WorkoutFragment::class, 14)
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
        tabLayout = view.findViewById(R.id.tab_layout)
        val tabLayoutMediator =
            TabLayoutMediator(tabLayout, viewPager, object : TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.setText("Level " + (position + 1))
                }
            })
        tabLayoutMediator.attach()
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
                val action = DayFragmentDirections.actionDayFragmentToWorkoutPreviewFragment(
                    workoutId,
                    currentSelectedLevel
                )
                findNavController().navigate(action)
            }

            else -> {
                Toast.makeText(requireContext(), "Фрагмент не поддерживается", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onExerciseClick(exercise: Exercise) {
        TODO("Not yet implemented")
    }
}