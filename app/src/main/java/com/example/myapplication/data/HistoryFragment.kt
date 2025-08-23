package com.example.myapplication.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.WorkoutApp
import com.example.myapplication.databinding.FragmentHistoryBinding
import com.example.myapplication.ui.EventDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "HISTORY"
        }

        val historyDao = (requireActivity().application as WorkoutApp).db.HistoryDao()
        setupCalendar(historyDao)
    }

    private fun setupCalendar(historyDao: HistoryDao) {
        val calendarView = binding.calendarView
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                historyDao.fetchAllDates().collect { historyList ->
                    Log.d("HistoryFragment", "Fetched history: $historyList") // Debug log
                    if (historyList.isNotEmpty()) {
                        binding.tvValueTotalNumberOfTrainings.text = historyList.size.toString()
                        binding.calendarView.visibility = View.VISIBLE
                        binding.tvValueTotalNumberOfTime.text =
                            historyList.sumOf { it.durationMinutes.toInt() }.toString()
                        binding.tvValueTotalNumberOfKcal.text = "676"//todo need to calculate
                        val dates = historyList.map { parseDate(it.date) }
                        calendarView.addDecorators(EventDecorator(requireContext(), dates))
                        calendarView.setOnDateChangedListener { _, date, _ ->
                            val events = historyList.filter { parseDate(it.date) == date }
                            showEventDialog(events)
                        }
                    } else {
                       // binding.calendarView.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun parseDate(dateStr: String): CalendarDay {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = sdf.parse(dateStr) ?: Date() // Fallback to today if parsing fails
        val calendar = Calendar.getInstance().apply { time = date }
        return CalendarDay.from(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun showEventDialog(events: List<HistoryEntity>) {
        // Implement a dialog to show workout details
        // Example: AlertDialog with event list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}