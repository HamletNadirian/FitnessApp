package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.WorkoutHistoryItem
import com.example.myapplication.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val items: List<WorkoutHistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvitem = binding.tvitem
        val tvposition = binding.tvposition
        val tvlvl = binding.tvlvl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvposition.text = (position + 1).toString()
        holder.tvitem.text = "📅 ${item.date}"
        holder.tvlvl.text = "🏋️‍♂️ Тренировка: ${item.workoutId}   ⭐ Уровень: ${item.workoutLevel}"
    }
}