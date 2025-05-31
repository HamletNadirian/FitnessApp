package com.example.myapplication.data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.WorkoutApp
import com.example.myapplication.databinding.FragmentHistoryBinding
import com.example.myapplication.ui.HistoryAdapter
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHistoryActivity)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "HISTORY"
        }
        binding.toolbarHistoryActivity.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        val historyDao = (requireActivity().application as WorkoutApp).db.HistoryDao()
        getAllCompleteDates(historyDao)
    }
    private fun getAllCompleteDates(historyDao: HistoryDao){

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                historyDao.fetchAllDates().collect { allCompleteDatesList ->
                    if (allCompleteDatesList.isNotEmpty()) {
                        binding?.tvhistory?.visibility = View.VISIBLE
                        binding?.rvhistory?.visibility = View.VISIBLE
                        binding?.tvnodataavailable?.visibility = View.GONE
                        binding?.rvhistory?.layoutManager = LinearLayoutManager(
                            this@HistoryFragment.requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )

                        val historyItems = allCompleteDatesList.map {
                            WorkoutHistoryItem(
                                date = it.date,
                                workoutId = it.workoutId,
                                workoutLevel = it.workoutLvl
                            )
                        }

                        //val historyAdapter =
                        binding?.rvhistory?.adapter = HistoryAdapter(historyItems)
                    } else {
                        binding?.tvhistory?.visibility = View.GONE
                        binding?.rvhistory?.visibility = View.GONE
                        binding?.tvnodataavailable?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}