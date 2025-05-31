package com.example.myapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.HistoryDao
import kotlinx.coroutines.launch

class CustomAdapter(

    private var itemList: List<FragmentItem>,
    private val historyDao: HistoryDao, // Передаем DAO
    private val lifecycleOwner: LifecycleOwner, // Для coroutines
    private val itemClickListener: OnItemClickListener // объявляем тут
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    private var currentLevel: Int = 1

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textDay: TextView = itemView.findViewById(R.id.tvDayNumber)
        private val countExercise: TextView = itemView.findViewById(R.id.tvWorkoutCount)
        private val ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)

        fun bind(item: FragmentItem) {
            textDay.text = item.numberDay
            countExercise.text = item.countExercise

            ivStatus.visibility = View.GONE
            checkWorkoutCompletion(item.workoutId)
            //ivStatus.setImageResource(R.drawable.ic_check)
            itemView.setOnClickListener {
                itemClickListener.onItemClick(item.fragmentClass, item.workoutId)

            }
        }

        private fun checkWorkoutCompletion(workoutId: Int) {
            lifecycleOwner.lifecycleScope.launch {
                historyDao.fetchAllDates().collect { completedWorkouts ->
                    val isCompleted = completedWorkouts.any { it.workoutId == workoutId.toString()&& it.workoutLvl==( currentLevel).toString() }
                    if (isCompleted) {
                        ivStatus.visibility = View.VISIBLE
                        ivStatus.setImageResource(R.drawable.ic_check)
                    } else {
                        ivStatus.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun updateCurrentLevel(level: Int) {
        currentLevel = level
        // Можно добавить логи для отладки
        Log.d("CustomAdapter", "Current level updated to: $level")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])

    }

    fun updateItems(newItems: List<FragmentItem>, level: Int) {
        itemList = newItems
        currentLevel = level
        Log.d("CustomAdapter", "updateItems(): new level = $level")
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = itemList.size
}